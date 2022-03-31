package mx.kodemia.appclimaedwin.ui.view.oneCall

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import mx.kodemia.appclimaedwin.R
import mx.kodemia.appclimaedwin.data.model.oneCall.Current
import mx.kodemia.appclimaedwin.databinding.ActivityOneCallBinding
import mx.kodemia.appclimaedwin.ui.viewModel.oneCall.OneCallViewModel
import java.text.SimpleDateFormat
import java.util.*

class OnecallActivityView : AppCompatActivity() {
    private lateinit var binding: ActivityOneCallBinding
    val viewModel: OneCallViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one_call)


        initBinding()
        observadores()
    }

    private fun observadores() {
        viewModel.getDatosOneCall()
        viewModel.oneCallEntity.observe(this){oneCall->
            val dateFormatter = SimpleDateFormat("dd MMMM, yyyy", Locale.ENGLISH)
            val updatedAt = dateFormatter.format(Date(oneCall.current.dt*1000))
            llenarReciclerView(oneCall.hourly)
        }
    }

    private fun llenarReciclerView(datosOne: List<Current>) {
        binding.apply {
            rvOnecall.hasFixedSize()
            rvOnecall.layoutManager = LinearLayoutManager(this@OnecallActivityView)
            rvOnecall.adapter = OneCallAdapter(this@OnecallActivityView,datosOne)
        }

    }
//    private fun initPredictionsRecyclerView(predictions: List<Current>){
//    binding.recyclerViewNextHours.apply {
//        layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
//        setHasFixedSize(true)
//        adapter = PredictionCardAdapter(this.context, predictions)

    private fun initBinding() {
        binding = ActivityOneCallBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}