package com.spinny.assignment.view.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.spinny.assignment.AppController
import com.spinny.assignment.databinding.ActivityDashboardBinding
import com.spinny.assignment.model.Car
import com.spinny.assignment.model.CarModelDetails
import com.spinny.assignment.model.entities.MyCars
import com.spinny.assignment.utilities.actionIntent
import com.spinny.assignment.utilities.toast
import com.spinny.assignment.view.adapter.CarAdapter
import com.spinny.assignment.view.base.BaseActivity
import com.spinny.assignment.viewmodel.DashboardViewModel
import com.spinny.assignment.viewmodel.DashboardViewModelFactory

class DashboardActivity : BaseActivity<ActivityDashboardBinding>(), CarAdapter.OnCarDelete {

    lateinit var carAdapter: CarAdapter
    lateinit var myCars: MyCars
    private val dashboardViewModel: DashboardViewModel by viewModels {
        DashboardViewModelFactory(
            (application as AppController).serverCalls!!,
            (application as AppController).carRepo
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dashboardViewModel.getMakeCarDetails()

        dashboardViewModel.makeCarDetails.observe(this) {
            bind.makeCarSpinner.setListItems(this, getCarMakes(it.Results))
            bind.makeCarSpinner.setOnItemClick { itemSelected, position ->
                myCars =
                    MyCars(null, it.Results[position - 1].Make_Name, 0, sessionConfig.getUserId())
                dashboardViewModel.getCarModels(it.Results[position - 1].Make_ID)
            }
        }

        dashboardViewModel.modelCarDetails.observe(this) {
            bind.modelCarSpinner.setListItems(this, getCarModel(it.Results))
            bind.modelCarSpinner.setOnItemClick { itemSelected, position ->
                myCars.modelName = it.Results[position - 1].Make_Name
            }
        }

        dashboardViewModel.getMyCars(sessionConfig.getUserId()).observe(this) {
            it?.let {
                carAdapter = CarAdapter(it, this)
                bind.recyclerView.layoutManager = LinearLayoutManager(this);
                bind.recyclerView.adapter = carAdapter

            }
        }

        bind.addCarButton.setOnClickListener {
            addCar()
            toast("Car Added")
        }
        bind.logoutButton.setOnClickListener {
            sessionConfig.setLoginStatus(false, 0)
            actionIntent(LoginActivity::class.java)
        }
    }

    private fun addCar() {
        if (!::myCars.isInitialized) return toast("Kindly select the car details")
        dashboardViewModel.createCar(myCars)

    }

    private fun getCarModel(results: List<CarModelDetails>): List<String> {
        return results.map { it.Make_Name }

    }

    private fun getCarMakes(results: List<Car>): List<String> {

        return results.map { it.Make_Name }
    }

    override fun getBinding() = ActivityDashboardBinding.inflate(layoutInflater)
    override fun deleted(id: Int) {
        dashboardViewModel.deleteCar(id)
    }
}