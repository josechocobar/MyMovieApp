package com.cuty.mymovieapp.data.remote.TimeController

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.LocalTime

@RequiresApi(Build.VERSION_CODES.O)
data class TimeControl(
    var startDate: LocalTime?=null,
    var endLifeDateTime: LocalTime?=null,
    var setFirstDb : Boolean = false
) {
    private fun getReferenceDate():LocalTime{
        return LocalTime.now()
    }
    private fun setEndLifeDb(referenceDate:LocalTime):LocalTime{
        return referenceDate.plusMinutes(5)
    }
    private fun isDbOld(questionDate: LocalTime,endLifeDateTime: LocalTime):Boolean{
        return questionDate.isAfter(endLifeDateTime)
    }
    fun setTimecontrol(){
        startDate = getReferenceDate()
        endLifeDateTime = setEndLifeDb(startDate!!)
    }

    /*** Return a boolean value, it indicates if a LocalDate passed is old
     * if is old , sets new time control parameters then return true
     * if is not old return false
     */
    fun decideTimeControl(questionDate: LocalTime): Boolean {
        return if (isDbOld(questionDate,endLifeDateTime!!)){
            setTimecontrol()
            true
        }else{
            false
        }
    }
}