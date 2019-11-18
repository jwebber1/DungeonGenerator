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

print 'Dungeon: ' + width + ' by ' + height
