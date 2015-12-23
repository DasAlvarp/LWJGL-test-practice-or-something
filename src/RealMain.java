import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.system.MemoryUtil.NULL;

/**
 * Created by alvarpq on 12/22/2015.
 */
public class RealMain {
    public RealMain() {
        glfwSetErrorCallback(errorCallback);
        if (glfwInit() != GLFW_TRUE) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        long window = glfwCreateWindow(1280, 720, "Plz work", NULL, NULL);
        glfwSetKeyCallback(window, keyCallback);
        if(window == NULL){
            glfwTerminate();
            throw new RuntimeException("Failed to create  the GLFW window");
        }

        //register key callback
        glfwSetKeyCallback(window, keyCallback);

        //creating OpenGL context
        glfwMakeContextCurrent(window);
        GL.createCapabilities();

        //rendering stuff now I think?
        while (glfwWindowShouldClose(window) != GLFW_TRUE) {
            double time = glfwGetTime();
            glfwSwapBuffers(window);
            glfwPollEvents();
        }

        //after the window is destroyed
        glfwDestroyWindow(window);
        keyCallback.release();
        glfwTerminate();
        errorCallback.release();
    }

    private GLFWErrorCallback errorCallback = GLFWErrorCallback.createPrint(System.err);

    //registering keys
    private GLFWKeyCallback keyCallback = new GLFWKeyCallback() {
        @Override
        public void invoke(long window, int key, int scancode, int action, int mods) {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_PRESS) {
                glfwSetWindowShouldClose(window, GLFW_TRUE);
            }
        }
    };

}
