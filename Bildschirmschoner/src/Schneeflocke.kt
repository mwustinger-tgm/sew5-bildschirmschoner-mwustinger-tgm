class Schneeflocke(var x: Double, var y: Double, var xSize: Int): Runnable {
    var f = false

    override fun run(){
        while(!f){
            Thread.sleep(10)
        }
        while(f){
            x+=(Math.random()*3).toInt()-1
            y+=Math.random()/2-0.1
            if (y<0)
                y=0.0
            if (x<0)
                x=0.0
            else if (x>xSize)
                x=xSize.toDouble()

            Thread.sleep(100)
        }
    }
}