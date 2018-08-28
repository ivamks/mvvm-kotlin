package com.katana.mvvm.ui.main.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.katana.mvvm.BR
import com.katana.mvvm.databinding.ItemStudentBinding
import com.katana.mvvm.model.Student
import kotlinx.android.synthetic.main.item_student.view.*

/**
 * Created by Kaz on 08:26 8/28/18
 */
class StudentAdapter : RecyclerView.Adapter<StudentAdapter.StudentHolder>() {

    private var listStudent: ArrayList<Student>?
    private lateinit var onItemClick: OnItemClick

    init {
        this.listStudent = arrayListOf()
    }

    fun setStudentList(listStudent: ArrayList<Student>) {
        this.listStudent = listStudent
        notifyDataSetChanged()
    }

    fun setOnItemClick(onItemClick: OnItemClick) {
        this.onItemClick = onItemClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val studentBinding = ItemStudentBinding.inflate(layoutInflater, parent, false)
        return StudentHolder(studentBinding)
    }

    override fun getItemCount(): Int = listStudent!!.size

    override fun onBindViewHolder(holder: StudentHolder, position: Int) {
        val student = listStudent!![position]
        holder.onBind(student)

        with(holder.itemView) {
            item_student.setOnClickListener { onItemClick.onItemClickListener(it, holder.adapterPosition) }
        }
    }

    inner class StudentHolder(private var itemStudentBinding: ItemStudentBinding) : RecyclerView.ViewHolder
    (itemStudentBinding.root) {

        fun onBind(student: Student) {
            itemStudentBinding.setVariable(BR.studentModel, student)
            itemStudentBinding.executePendingBindings()
        }
    }

    interface OnItemClick {

        fun onItemClickListener(view: View, pos: Int)
    }
}