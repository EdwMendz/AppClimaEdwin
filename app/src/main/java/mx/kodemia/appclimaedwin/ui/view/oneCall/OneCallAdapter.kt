package mx.kodemia.appclimaedwin.ui.view.oneCall

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import mx.kodemia.appclimaedwin.R
import mx.kodemia.appclimaedwin.data.model.oneCall.Current
import mx.kodemia.appclimaedwin.databinding.CardElementosBinding
import java.text.SimpleDateFormat
import java.util.*


class OneCallAdapter(val context: Context, val current: List<Current>) :
    RecyclerView.Adapter<OneCallAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.card_elementos,parent,false)
        return ViewHolder(view,context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.render(current[position])
       }


    override fun getItemCount(): Int = current.size


    class ViewHolder(view: View, var context:Context):RecyclerView.ViewHolder(view) {
        val binding = CardElementosBinding.bind(view)

        fun render(current:Current){
            val icon = current.weather.first().icon.replace('n','d')
            val iconResource = context.resources.getIdentifier("ic_weather_$icon","drawable",context.packageName)
            val dateFormatter = SimpleDateFormat("hh:mm a", Locale.ENGLISH)
            val hour = dateFormatter.format(Date(current.dt*1000))
            binding.apply {
                ivOneCall.load(iconResource)
                tvPrediccion.text = hour
                tvPrediccionValor.text = context.getString(R.string.formatted_temp, current.temp.toString())
            }

        }

    }
    }
