import android.os.Parcel
import android.os.Parcelable

/**
 * Trieda Cvik reprezentuje cvik pre apku.
 * Obsahuje informácie o obrázku cviku, názve, popise a názve videa.
 *
 * Implementuje rozhranie Parcelable na umožnenie prenosu inštancií cviku medzi aktivitami.
 *
 * @property obrazokId Identifikátor obrázka cviku.
 * @property nazov Názov cviku.
 * @property popis Popis cviku.
 * @property videoName Názov videa cviku.
 */
data class Cvik(
    val obrazokId: Int,
    val nazov: String,
    val popis: String,
    val videoName: String
) : Parcelable {

    /**
     * Konštruktor triedy Cvik pre získanie hodnôt zo zdroja Parcel.
     *
     * @param parcel Parcel obsahujúci hodnoty cviku.
     */
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    )

    /**
    * Zapíše hodnoty cviku do zdroja Parcel.
    *
    * @param parcel Parcel, do ktorého sa majú zapísať hodnoty cviku.
    * @param flags Príznaky.
    */
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(obrazokId)
        parcel.writeString(nazov)
        parcel.writeString(popis)
        parcel.writeString(videoName)
    }

    /**
     * Vráti príznaky objektu.
     *
     * @return Príznaky objektu.
     */
    override fun describeContents(): Int {
        return 0
    }

    /**
     * Objekt CREATOR slúži na vytvorenie inštancie cviku z objektu Parcel
     * a na vytvorenie poľa inštancií cviku.
     */
    companion object CREATOR : Parcelable.Creator<Cvik> {

        /**
         * Vytvorí inštanciu cviku z objektu Parcel.
         *
         * @param parcel Parcel obsahujúci hodnoty cviku.
         * @return Inštancia cviku.
         */
        override fun createFromParcel(parcel: Parcel): Cvik {
            return Cvik(parcel)
        }

        /**
         * Vytvorí pole inštancií cviku.
         *
         * @param size Veľkosť poľa.
         * @return Pole inštancií cviku.
         */
        override fun newArray(size: Int): Array<Cvik?> {
            return arrayOfNulls(size)
        }
    }
}
