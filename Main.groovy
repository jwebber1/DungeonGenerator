
//variable to see the output of various print statements
seeOutput = false

//instantiate a new random object
rand = new Random()

println "Please enter the desired size of your dungeon:"

width = 0
height = 0
monsters = -1

while(width < 4){
    print "Width: "

    try {
        width = System.in.newReader().readLine() as Integer
        if(width < 4) println "Dungeon size must be an integer greater than or equal to 4. Please try again."
    }
    catch (NumberFormatException err){ println "That number seemed to have been formatted incorrectly. Please try again." }
}
while(height < 4){
    print "Height: "

    try {
        height = System.in.newReader().readLine() as Integer
        if(height < 4) println "Dungeon size must be an integer greater than or equal to 4. Please try again."
    }
    catch (NumberFormatException err){ println "That number seemed to have been formatted incorrectly. Please try again." }
}
/*
while(monsters < 0){
    print("Number of Monsters: ")
    try { monsters = System.in.newReader().readLine() as Integer }
    catch (NumberFormatException err){ println("It appears you did not put in 0 or an integer greater than 0. Please try again.") }
}
*/

List environment = []

fillEnvironment(environment, width, height)
printEnvironment(environment)

def printEnvironment(environment){
    for (row in environment){
        for(item in row){ printf('%s  ',item) }
        println()
    }
}

def fillEnvironment(environment, row, col){

    //https://www.freecodecamp.org/news/how-to-make-your-own-procedural-dungeon-map-generator-using-the-random-walk-algorithm-e0085c8aa9a/
    //1. make a two dimensional map of walls
    for(int i = 0; i < col; i++){
        environment.add([])
        for(int j = 0; j < row; j++){ environment[i][j] = 'W' }
    }

    //to be used with randoms below
    maxLength = Math.min(row, col) - 1

    //2. Choose a random starting point on the map
    //random starting column
    temp = rand.nextInt(col)
    while(temp == 0 || temp == col - 1) temp = rand.nextInt(col)   //guarentees starting point will not be on edge
    randCol = temp

    //random starting row
    temp = rand.nextInt(row)
    while(temp == 0 || temp == row - 1) temp = rand.nextInt(row)    //guarentees starting point will not be on edge
    randRow = temp

    if(seeOutput) println("randCol = " + randCol + "\nrandRow = " + randRow)

    //super small, simple logic for number of tunnels, will probably change
    //if(row+col < 10) numTunnels = (int)Math.sqrt(row*col)
    //else numTunnels = (int)(row*col/Math.sqrt(row*col))

    if(row+col <= 20)  numTunnels = row*col
    else  numTunnels = (int)(row*col - (row*col)*Math.pow(0.995, Math.min(row,col)))

    //numTunnels = (int)(row*col*Math.pow(0.90, Math.min(row,col)))
    if(seeOutput) println "numTunnels = " + numTunnels

    //variable to hold the old direction so that we don't duplicate directions each iteration
    oldDir = -1

    //go through all the specified tunnels
    for(int i = 0; i < numTunnels; i++){
        if(seeOutput) println "i = " + i

        //4. Choose a random length from maximum allowed length
        randLength = rand.nextInt(maxLength) + 1 // the + 1 is in case the random number chosen is 0

        if(seeOutput) println "randLength = " + randLength

        //random direction between 0-3: 0 = up, 1 = right, 2 = down, 3 = left
        temp = rand.nextInt(4)
        while(temp == oldDir) temp = rand.nextInt(4)
        randDir = temp

        if(seeOutput) println "randDir = " + getDir(randDir)

        //create the tunnel
        for(int j = 0; j < randLength; j++){
            if(seeOutput) println "atEdge = " + atEdge(randDir, randRow, randCol, row, col)

            //if not at the edge of the environment...
            if(!atEdge(randDir, randRow, randCol, row, col)){
                if(seeOutput) print j + " "
                //set the character at the given row and column
                environment[randRow][randCol] = "."

                //move in the next direction
                if(randDir == 0) { randRow -= 1 }       //move up one
                else if(randDir == 1) { randCol += 1}   //move right one
                else if(randDir == 2) { randRow += 1}   //move down one
                else { randCol -= 1}                    //move left 1
            }

            //else, break
            else { break; }
        }

        //set the old direction to the most recent
        oldDir = randDir

        if(seeOutput) println()
    }
}//end of fillEnvironment

//predicate function to determine if the input row and column are at the edge of the environment
def atEdge(direction, row, col, maxRow, maxCol){

    //if heading north and at the top of the environment, return true, otherwise return false
    if(direction == 0){ return row == 1 ? true : false }

    //if heading east and at the right of the environment, return true, otherwise return false
    else if(direction == 1){ return col == maxCol - 2 ? true : false }

    //if heading south and at the bottom of the environment, return true, otherwise return false
    else if(direction == 2){ return row == maxRow - 2 ? true : false }

    //if heading east and at the left of the environment, return true, otherwise return false
    else{ return col == 1 ? true : false }
}

//small function for testing purposes to print the direction of movement
def getDir(direction){
    if(direction == 0){ return "up" }
    else if(direction == 1){ return "right" }
    else if(direction == 2){ return "down" }
    else{ return "left" }
}