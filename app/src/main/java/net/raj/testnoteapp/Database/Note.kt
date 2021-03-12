package net.raj.testnoteapp.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
class Note (@ColumnInfo( name = "note") val note : String ){
    @PrimaryKey(autoGenerate = true) var id =0
}