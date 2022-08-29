# booky
 A small application which (sort of) helps with my books. :)
 
Saves general metadata information and generates appropriate QR-codes.

These QR-codes can then be attached to the books to quickly scan and retrieve the stored metadata.

Currently stored data:

- book name
- series name
- series entry
- release date
- edition
- language
- author
- artist
- chapters
- pages

TBD:

- publisher
- ISBN
- ...and more

### Run (local)
- Tested primarily with JDK 18.

Clone the repo, go into the root directory and run the following *__maven__* goal:

    javafx:run


## How to

### Generate QR:

![generate qr](/docs/images/booky_generateqr.JPG)

### Read QR:

![read qr](/docs/images/booky_readqr.JPG)

Alternatively, any other QR-code reader can be used to read the stored data.
