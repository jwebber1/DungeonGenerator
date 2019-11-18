/*
Sprint 1 details:
    1) Accept user input
    2) Validate user input
        a) If non-validated, give another chance
    3) Print something out to the console
*/
println("Please enter the desired size of your dungeon:")

def width = 0, height = 0, monsters = -1

while(width < 1){
    print("Width: ")
    try {  width = System.in.newReader().readLine() as Integer }
    catch (NumberFormatException err){ println("It appears you did not put in an integer greater than 0. Please try again.") }
}
while(height < 1){
    print("Height: ")
    try { height = System.in.newReader().readLine() as Integer }
    catch (NumberFormatException err){ println("It appears you did not put in an integer greater than 0. Please try again.") }
}
while(monsters < 0){
    print("Number of Monsters: ")
    try { monsters = System.in.newReader().readLine() as Integer }
    catch (NumberFormatException err){ println("It appears you did not put in 0 or an integer greater than 0. Please try again.") }
}

//--------------------new -----------------------------

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
    rand = new Random()

    for(int i = 0; i < col; i++){
        environment.add([])

        for(int j = 0; j < row; j++){
            r = rand.nextInt(100)
            c = ''

            if(r < 50){ c = 'P' }
            else if(r < 90){ c = 'W' }
            else{ c = 'T' }

            environment[i][j] = c
        }
    }
}
//--------------end of new------------