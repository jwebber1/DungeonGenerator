class Environment {

    //instantiate a new random object for the class
    def rand = new Random()

    def row = 0
    def col = 0
    List environment = []

    Environment(width, height) {
        this.row = width
        this.col = height
    }

    public void printEnvironment(){
        for(int i = 0; i < col; i++){
            List temp = environment.get(i)
            for(int j = 0; j < row; j++) printf('%s ', environment[i][j])
            println()
        }
    }

    public void fillEmptyEnvironment(){
        for(int i = 0; i < col; i++){
            environment.add([])
            for(int j = 0; j < row; j++) environment[i][j] = 'W'
        }
    }

    public void fillEnvironment(){
        fillEmptyEnvironment()

        //to be used with randoms below
        def maxLength = Math.min(row, col) - 1

        //random starting column
        def temp = rand.nextInt(col)
        while(temp == 0 || temp == col - 1) temp = rand.nextInt(col)    //guarantees starting point will not be on edge
        def randCol = temp

        //random starting row
        temp = rand.nextInt(row)
        while(temp == 0 || temp == row - 1) temp = rand.nextInt(row)    //guarantees starting point will not be on edge
        def randRow = temp

        //super small, simple logic for number of tunnels, will probably change
        def numTunnels
        if(row+col <= 20) numTunnels = row*col
        else  numTunnels = (int)(row*col - (row*col)*Math.pow(0.995, Math.min(row,col)))

        //variable to hold the old direction so that we don't duplicate directions each iteration
        def oldDir = -1

        //go through all the specified tunnels
        for(int i = 0; i < numTunnels; i++){

            //choose a random length from maximum allowed length
            def randLength = rand.nextInt(maxLength) + 1 // the + 1 is in case the random number chosen is 0

            //random direction between 0-3: 0 = up, 1 = right, 2 = down, 3 = left
            temp = rand.nextInt(4)
            while(temp == oldDir) temp = rand.nextInt(4)
            def randDir = temp

            //create the tunnel
            for(int j = 0; j < randLength; j++){

                //if not at the edge of the environment...
                if(!atEdge(randDir, randRow, randCol, row, col)){

                    //set the character at the given row and column
                    environment[randCol][randRow] = "."

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
        }
    }

    //predicate function to determine if the input row and column are at the edge of the environment
    public boolean atEdge(direction, row, col, maxRow, maxCol){

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
    public String getDir(direction){
        if(direction == 0){ return "up" }
        else if(direction == 1){ return "right" }
        else if(direction == 2){ return "down" }
        else{ return "left" }
    }

    public List getEnvironment(){ return environment; }
}
