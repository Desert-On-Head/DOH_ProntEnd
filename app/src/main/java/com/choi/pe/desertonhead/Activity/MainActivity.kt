package com.choi.pe.desertonhead.Activity

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.choi.pe.desertonhead.R
import app.akexorcist.bluetotohspp.library.BluetoothSPP
import app.akexorcist.bluetotohspp.library.BluetoothState
import org.jetbrains.anko.toast
import app.akexorcist.bluetotohspp.library.DeviceList
import android.content.Intent
import android.app.Activity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast
import com.choi.pe.desertonhead.Util.DataManager


class MainActivity : AppCompatActivity() {
    val bt = BluetoothSPP(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var check:Boolean = false
        val dataManager: DataManager = DataManager(applicationContext)
        nicknameText.text = "NICK : " + dataManager.getNickname()
        idText.text = "ID : " + dataManager.getId()

        if(!bt.isBluetoothAvailable()){
            Toast.makeText(this@MainActivity, "블루투스를 켜주세요", Toast.LENGTH_SHORT).show()
        }

        bt.setBluetoothStateListener { state ->
            if (state == BluetoothState.STATE_CONNECTED)
                Log.i("Check", "State : Connected")
            else if (state == BluetoothState.STATE_CONNECTING)
                Log.i("Check", "State : Connecting")
            else if (state == BluetoothState.STATE_LISTEN)
                Log.i("Check", "State : Listen")
            else if (state == BluetoothState.STATE_NONE)
                Log.i("Check", "State : None")
        }

        bt.setOnDataReceivedListener (object : BluetoothSPP.OnDataReceivedListener{
            override fun onDataReceived(data: ByteArray?, message: String?) {
                Log.e("message", message)
                val array = message!!.split(",")
                noConnectText.text = "온도 : ${array[0]}°C\n습도 : ${array[1]}%"
                connectImg.setImageResource(R.drawable.brain_connect_icon)
                noConnectText.text = "블루투스가 연결되었습니다."
            }
        })

        bt.setBluetoothConnectionListener(object : BluetoothSPP.BluetoothConnectionListener {
            override fun onDeviceConnected(name: String, address: String) {
                Log.i("Check", "Device Connected!!")
                Toast.makeText(this@MainActivity, "Device Connected!!", Toast.LENGTH_SHORT).show()
                connectImg.setImageResource(R.drawable.brain_connect_icon)
                noConnectText.text = "블루투스가 연결되었습니다."
            }

            override fun onDeviceDisconnected() {
                Log.i("Check", "Device Disconnected!!")
                Toast.makeText(this@MainActivity, "Device DisConnected!!", Toast.LENGTH_SHORT).show()
            }

            override fun onDeviceConnectionFailed() {
                Log.i("Check", "Unable to Connected!!")
                Toast.makeText(this@MainActivity, "Unable to Connected!!", Toast.LENGTH_SHORT).show()
            }
        })

        bt.setAutoConnectionListener(object : BluetoothSPP.AutoConnectionListener {
            override fun onNewConnection(name: String, address: String) {
                Log.i("Check", "New Connection - $name - $address")
                Toast.makeText(this@MainActivity, "New Connection - $name - $address", Toast.LENGTH_SHORT).show()
                bt.send("Bluetooth Connected", false)
            }

            override fun onAutoConnectionStarted() {
                Log.i("Check", "Auto menu_connection started")
            }
        })

        checkBtn.setOnClickListener {
            if(check){
                check=false
                bt.send("0",false)
            }else{
                check=true
                bt.send("1",true)
            }
        }
    }

    override fun onPause() {
        bt.stopService()
        super.onPause()
    }

    override fun onStart() {
        super.onStart()
        bt.setupService()
        bt.startService(BluetoothState.DEVICE_OTHER)
        bt.autoConnect("SunSet")
        Log.e("STATE", "onStart")

    }
}
