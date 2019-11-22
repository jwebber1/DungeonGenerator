import groovy.util.GroovyTestSuite

class TestClass extends GroovyTestCase {

    def testEnv = new Environment(5, 5)

    void testEnvironmentSize(){
        testEnv.fillEmptyEnvironment()
        assertEquals(testEnv.getEnvironment().size(), 5)
    }

    void testAtEdgeTrue(){
        testEnv.fillEmptyEnvironment()
        assertEquals(testEnv.atEdge(0, 1, 1, 5, 5), true)
    }

    void testAtEdgeFalse(){
        testEnv.fillEmptyEnvironment()
        assertEquals(testEnv.atEdge(2, 1, 1, 5, 5), false)
    }

    void testGetDirUp(){
        testEnv.fillEmptyEnvironment()
        assertEquals(testEnv.getDir(0), "up")
    }

    void testGetDirRight(){
        testEnv.fillEmptyEnvironment()
        assertEquals(testEnv.getDir(1), "right")
    }

    void testGetDirDown(){
        testEnv.fillEmptyEnvironment()
        assertEquals(testEnv.getDir(2), "down")
    }

    void testGetDir(){
        testEnv.fillEmptyEnvironment()
        assertEquals(testEnv.getDir(3), "left")
    }
}