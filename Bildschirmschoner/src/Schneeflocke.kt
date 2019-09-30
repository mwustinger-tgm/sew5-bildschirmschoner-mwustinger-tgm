/**
 * Schneeflocke implementiert Runnable um durch einen Executor gestartet werden zu können.
 * Der Parameter xSize gibt die maximale Arealgröße an, damit die Schneflocke nicht aus dem Bild fliegt.
 * @author Martin Wustinger
 * @version 30-09-2019 (letzte Überarbeitung bevor Upload,(Kommentare))
 */
class Schneeflocke(var x: Double, var area: Area): Runnable {
    var y = 0.0
    companion object {
        @Volatile var counter=0;
    }

    override fun run(){
        // Wait for Main-Thread
        while(!area.flag){
            Thread.sleep(10)
        }
        loop@ while(area.flag){
            //Generate Random Values
            x+=(Math.random()*3).toInt()-1
            y+=Math.random()/2-0.1
            // Fix the Values
            if (y<0)
                y=0.0
            else if (y>=area.ySize) {
                counter++
                break@loop
            }
            if (x<0)
                x=0.0
            else if (x>area.xSize)
                x=area.xSize.toDouble()

            Thread.sleep(100)
        }
    }
}