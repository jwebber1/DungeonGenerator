/*
This code goes through several loops to get the user specified height, length, and rooms in groovy.
Mason Booth
 */

println "How big do you want your dungeon area to be..."

while (true) {
    println "Height? "
    def x = System.in.newReader().readLine()
    if (x.isInteger()) {
        println "You want a height of " + x + "."
        break
    } else {
        println("Please enter only a number.")
    }
}

while (true) {
    println "And Length? "
    def y = System.in.newReader().readLine()
    if (y.isInteger()) {
        println "You want a length of " + y + "."
        break
    } else {
        println("Please enter only a number.")
    }
}

while (true) {
    println "How many rooms? "
    def z = System.in.newReader().readLine()
    if (z.isInteger()) {
        println "You want " + z + " rooms."
        break
    } else {
        println("Please enter only a number.")
    }
}
