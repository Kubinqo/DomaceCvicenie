import android.os.Parcel
import android.os.Parcelable

data class Cvik(
    val obrazokId: Int,
    val nazov: String,
    val popis: String,
    val videoName: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(obrazokId)
        parcel.writeString(nazov)
        parcel.writeString(popis)
        parcel.writeString(videoName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Cvik> {
        override fun createFromParcel(parcel: Parcel): Cvik {
            return Cvik(parcel)
        }

        override fun newArray(size: Int): Array<Cvik?> {
            return arrayOfNulls(size)
        }
    }
}
