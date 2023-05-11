# DESCRIPTION

- Files
  - App-specific storage
    - Internal
    - External
  - Shared storage
    - Media & Doc
- Preferences
- Databases

... take a look to https://github.com/PedemonteGiacomo/MobileDevelopment/blob/main/slides/09.0B%20Data%20Storage.pdf

# Always check availability of storage

Because the external storage may be unavailable
- such as when the user has mounted the storage to a PC or has removed the SD card that provides the external storage

You should always verify that the volume is available before accessing it

    /* Checks if external storage is available for read and write */

    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        // if the returned state is equal to MEDIA_MOUNTED, 
        // then you can read and write your files
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
            Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

---

Second version of the method

    /* Checks if external storage is available to at least read */
    
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        /*
        Returns the current state of the primary external storage media…
        Otherwise use (e.g., for SD card etc):
            String getExternalStorageState (File path)
        */
        if (Environment.MEDIA_MOUNTED.equals(state) ||
            Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
    return false;
    }

# Accessing External storage directories

1. Get a path : 
   
   getExternalFilesDir()

2. Create file

Example:
    // get the path
    File path = getExternalFilesDir(Environment.DIRECTORY_PICTURES); // with the dot will display all the FilesDir
    // create the file
    File file = new File(path, "DemoPicture.jpg");

# Select a physical storage location

    File[] externalStorageVolumes = ContextCompat.getExternalFilesDirs(getApplicationContext(), Environment.DIRECTORY_PICTURES);

    // probably a partition of the device internal memory as external storage 
    File pathPrimaryExternalStorage = externalStorageVolumes[0];
    // probably this is the SD card
    File pathSecondaryExternalStorage = externalStorageVolumes[1];

the first element in the returned array (i.e., [0]) is considered the primary external storage volume

This means that the device has multiple physical volumes that could contain external storage, so you need to select which one to use for your app-specific storage

# How much storage left?

If you know the size of the file, check against space

    getFreeSpace()   
    getTotalSpace(). 

If you do not know how much space is needed
    
    try/catch IOException

# Delete files

External storage:

    myFile.delete();

Internal storage:

    myContect.deletefile(fileName);

---

DO NOT DELETE THE USER'S FILES

When the user uninstalls your app, your app's private storage directory and all its contents are delated.

So, don't use private storage for content that belongs to the user!

For example:
- Photos captured or edited with your app
- Music the user has purchased with your ap

