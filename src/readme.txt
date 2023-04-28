Local Testing:
    1. Open the src directory in a cli of your choice.
    2. Run the following command:
        javac -d ./build fireice/*.java
    3. Open the build directory.
    4. Run the following command:
        jar cmvf META-INF/MANIFEST.MF 9mm.jar  *
    5. Run the following command:
        java -jar 9mm.jar