import java.util.concurrent.*

class Area(var anz: Int, var xSize: Int, var ySize:Int){
    var pool = Executors.newCachedThreadPool()
    var threads = ArrayList<Schneeflocke>()
    @Volatile var flag = false

    fun start(){
        for (i in 0 until anz){
            threads.add(Schneeflocke(xSize.toDouble()*Math.random(), this))
            pool.submit(threads[i])
            println("Thread gestarted...")
        }
        //Alle Threads warten auf das Signal vom Mainthread
        Thread.sleep(1000)
        flag=true
        println("Thread flag auf true gesetzt")

        //Informations about the area size
        println("Feld: x: $xSize, y: $ySize")

        //Main-loop
        loop@ while (true) {
            var flag = true
            for (y in 0..ySize) {
                for (x in 0..xSize){
                    var str = if (y == ySize) "-" else " "
                    for (f in threads)
                        if (f.x.toInt() == x && f.y.toInt() == y)
                            str = "*"
                    print(str)
                }
                println("")
            }
            println("")
            if (Schneeflocke.counter==anz)
                break@loop
            Thread.sleep(500)
        }

        //Shutting the whole program down
        println("Schleife beendet")
        pool.shutdown()
    }
}

fun main(){
    val a = Area(5, 40, 10)
    a.start()
}