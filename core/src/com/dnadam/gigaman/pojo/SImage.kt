package com.dnadam.gigaman.pojo

import com.badlogic.gdx.math.Vector2
import com.dnadam.gigaman.entities.Enemy
import com.dnadam.gigaman.entities.GigaMan
import com.dnadam.gigaman.entities.Portal
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SImage(@SerializedName("uniqueId")
                  @Expose
                  var uniqueId: Int = 0,

                  @SerializedName("itemIdentifier")
                  @Expose
                  var itemIdentifier: String = "",

                  @SerializedName("tags")
                  @Expose
                  var tags: List<Any> = listOf(),

                  @SerializedName("customVars")
                  @Expose
                  var customVars: String = "",
                  @SerializedName("x")
                  @Expose
                  var x: Float = 0f,

                  @SerializedName("y")
                  @Expose
                  var y: Float = 0f,

                  @SerializedName("originX")
                  @Expose
                  var originX: Float = 0f,

                  @SerializedName("originY")
                  @Expose
                  var originY: Float = 0f,

                  @SerializedName("zIndex")
                  @Expose
                  var zIndex: Int = 0,

                  @SerializedName("layerName")
                  @Expose
                  var layerName: String = "",

                  @SerializedName("imageName")
                  @Expose
                  var imageName: String = "") {

    fun mapToPlayer(): GigaMan {
        return GigaMan(Vector2(x, y))
    }

    fun mapToEnemy(): Enemy {
        val args = customVars.split(";")
        val argsMap = HashMap<String, Float>()
        args.forEach { arg: String ->
            val split = arg.split(":")
            if (split.size > 1) argsMap[split[0]] = split[1].toFloat()
        }
        return Enemy(itemIdentifier.toLong(),
                argsMap["numberOfFrames"]?.toInt() ?: 1,
                Vector2(x, y),
                Pair(Vector2(argsMap["fromX"] ?: 0f, argsMap["fromY"] ?: 0f),
                        Vector2(argsMap["toX"] ?: 0f, argsMap["toY"] ?: 0f)),
                (argsMap["life"] ?: 1f).toInt()
        )
    }

    fun mapToPortal(): Portal {
        return Portal(Vector2(x, y))
    }
}