package com.spinny.assignment.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.spinny.assignment.databinding.CarLayoutBinding
import com.spinny.assignment.model.entities.MyCars

class CarAdapter(private val list: List<MyCars>, private val onCarDelete: OnCarDelete) :
    RecyclerView.Adapter<CarAdapter.CarViewHolder>() {


    class CarViewHolder(private val bind: CarLayoutBinding) :
        RecyclerView.ViewHolder(bind.root) {

        fun bindLayout(myCars: MyCars, onCarDelete: OnCarDelete) {
            bind.carMakeTextview.text = myCars.makeCar
            bind.carModelTextview.text = myCars.modelName
            bind.deleteImageButton.setOnClickListener {
                onCarDelete.deleted(myCars.id)
            }
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        return CarViewHolder(
            CarLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.bindLayout(list[position], onCarDelete)
    }

    override fun getItemCount() = list.size

    interface OnCarDelete {
        fun deleted(id: Int)
    }
}