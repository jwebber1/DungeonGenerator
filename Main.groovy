class Main {
    public static void main(String[] args) {
        def width = 0, height = 0

        while (width < 4) {
            print "Width: "
            try {
                width = System.in.newReader().readLine() as Integer
                if(width < 4) println "Dungeon width must be an integer greater than or equal to 4. Please try again."
            }
            catch (NumberFormatException err) { println "That number seemed to have been formatted incorrectly. Please try again." }
        }
        while (height < 4) {
            print "Height: "
            try {
                height = System.in.newReader().readLine() as Integer
                if(height < 4) println "Dungeon height must be an integer greater than or equal to 4. Please try again."
            }
            catch (NumberFormatException err) { println "That number seemed to have been formatted incorrectly. Please try again." }
        }

        def environment = new Environment(width, height)
        environment.fillEnvironment()
        environment.printEnvironment()
    }
}
