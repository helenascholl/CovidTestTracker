package at.htl.covidtesttracker

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.time.LocalDateTime

@Parcelize
class Test(
    val id: String,
    val date: LocalDateTime,
    val result: TestResult,
    val location: String
) : Parcelable {
}