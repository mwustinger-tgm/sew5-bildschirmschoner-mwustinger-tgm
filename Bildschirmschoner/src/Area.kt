import java.util.concurrent.*

class Area(var anz: Int, var xSize: Int, var ySize:Int){
    var pool = Executors.newCachedThreadPool()
    var threads = ArrayList<Schneeflocke>()


    fun start(){

        for (i in 0 until anz){
            threads.add(Schneeflocke(xSize.toDouble()*Math.random(), 0.0, xSize))
            pool.submit(threads[i])
            println("Thread gestarted...")
        }
        Thread.sleep(100)
        for (f in threads) {
            f.f = true
        }
        println("Thread flag auf true gesetzt")
        println("Feld: x: $xSize, y: $ySize")
        loop@ while (true) {
            var flag = true
            for (y in 0..ySize) {
                for (x in 0..xSize){

                    var str = " "
                    for (f in threads) {
                        if (f.y.toInt()>=ySize){
                            f.f = false
                        }else{
                            flag = false
                        }
                        if (f.x.toInt() == x && f.y.toInt() == y) {
                            str = "*"
                        }
                    }
                    print(str)

                }
                println("")
            }
            for (i in 0..xSize){
                print("-")
            }
            Thread.sleep(500)
            println("")
            if (flag){
                break@loop
            }
        }
        println("Schleife beendet")
        pool.shutdown()
    }
}

fun main(){
    val a = Area(4, 40, 10)
    a.start()
}