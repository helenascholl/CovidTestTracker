package at.htl.covidtesttracker

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.time.LocalDateTime

@Parcelize
class Test(
    private val id: String,
    private val date: LocalDateTime,
    private val result: TestResult,
    private val location: String
) : Parcelable {
}