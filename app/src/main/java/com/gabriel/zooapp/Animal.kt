package com.gabriel.zooapp


class Animal {
    var name:String ?= null
    var description:String ?= null
    var image:Int ?= null
    var predator:Boolean ?= null

    constructor(name:String, description:String, image:Int, predator:Boolean){
        this.name = name
        this.description = description
        this.image = image
        this.predator = predator
    }

}