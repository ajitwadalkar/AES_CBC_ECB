import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.util.Scanner;

public class aes {
    //Initializing the Scanner
    static final Scanner scanner = new Scanner(System.in);

    //Main class
    public static void main(String[] args) throws Exception {

        //Printing Initial values
        ReadFiles();

        new FileReadWrite();


        int option;
        do {
            //Choosing the function
            System.out.println("\nFollowing are the functionalities in the code, choose anyone:\n" +
                    "1.Generate new random Key\n" +
                    "2.Encode plaintext\n" +
                    "3.Decode ciphertext\n" +
                    "4.Compare AES CBC and ECB\n" +
                    "5.Read current files\n" +
                    "6.Exit\n" +
                    "Please choose the value in between 1,2,3,4,5 or 6");

            int funcSel = promptChoice(1, 6);

            subMain(funcSel);

            System.out.println("\nDo you want to re-run the program?");
            System.out.println("Please choose from the following.\n1. Yes\n2. No");
            option =  promptChoice(1, 2);
        } while (option == 1);

        System.out.println("You chose to terminate the program. Thank you.");
    }

    //Function to read data in all the files by using FileReadWrite class.
    static void ReadFiles(){
        System.out.println("\n******************************************************************************************************************");
        System.out.println("Current Data in all files:");
        System.out.println("plaintext.txt : "+FileReadWrite.ReadFile("plaintext.txt"));
        System.out.println("ciphertext.txt : "+FileReadWrite.ReadFile("ciphertext.txt"));
        System.out.println("key.txt : "+FileReadWrite.ReadFile("key.txt"));
        System.out.println("iv.txt : "+FileReadWrite.ReadFile("iv.txt"));
        System.out.println("result.txt : "+FileReadWrite.ReadFile("result.txt"));
        System.out.println("********************************************************************************************************************\n");
    }


    //Function to get input from the user to select the functions.
    private static int promptChoice(int min, int max) {
        for (; ; ) {
            if (!scanner.hasNextInt()) {
                System.out.println("You must enter a number");
            } else {
                int option = scanner.nextInt();
                if (option >= min && option <= max)
                    return option;
            }
            scanner.nextLine();
        }
    }


    public static void subMain(int option) throws Exception {
        long duration, endTime, startTime;
        //Calling Keygen function
        if (option == 1) {
            System.out.println("\n******************************************************************************************************************");
            System.out.println("Selected Function is KeyGen.");
            String keyText = FileReadWrite.ReadFile("key.txt");
            System.out.println("Key text in the file key.txt: " + keyText);
            startTime = System.nanoTime();
            EncDecFunction.keyGen();
            endTime = System.nanoTime();
            duration = (endTime - startTime);
            double seconds = (double) duration / 1000000000.0;
            keyText = FileReadWrite.ReadFile("key.txt");
            System.out.println("New Key text in the file key.txt: " + keyText);
            System.out.println("The time it took to run KeyGEN function  is: " + seconds + "Seconds");
            System.out.println("********************************************************************************************************************\n");
        }
        //Calling Encryption function
        else if (option == 2) {
            System.out.println("\n******************************************************************************************************************");
            System.out.println("Selected Function is Encryption.");
            String plainText = FileReadWrite.ReadFile("plaintext.txt");
            System.out.println("Plain text in the file plaintext.txt: " + plainText);
            startTime = System.nanoTime();
            byte[] Encrypted = EncDecFunction.encryptCBC(plainText);
            endTime = System.nanoTime();
            duration = (endTime - startTime);
            double seconds = (double) duration / 1000000000.0;
            String encData = DatatypeConverter.printHexBinary(Encrypted);
            FileReadWrite.WriteFile("ciphertext.txt", encData);
            System.out.println("Encrypted Text is: " + encData);
            System.out.println("The time it took to run Encryption function  is: " + seconds + "Seconds");
            System.out.println("********************************************************************************************************************\n");
        }
        //Calling Decryption function
        else if (option == 3) {
            System.out.println("\n******************************************************************************************************************");
            System.out.println("Selected Function is Decryption.");
            String  cipherText = FileReadWrite.ReadFile("ciphertext.txt");
            System.out.println("Cipher text in the file ciphertext.txt: " + cipherText);
            startTime = System.nanoTime();
            String plainText = EncDecFunction.decryptCBC(DatatypeConverter.parseHexBinary(cipherText));
            endTime = System.nanoTime();
            duration = (endTime - startTime);
            double seconds = (double) duration / 1000000000.0;
            FileReadWrite.WriteFile("result.txt", plainText);
            System.out.println("Decrypted Text is: " + plainText);
            System.out.println("The time it took to run Decryption function  is: " + seconds + "Seconds");
            System.out.println("********************************************************************************************************************\n");
        }
        //CBC ECB comparison
        else if (option == 4) {
            System.out.println("\n******************************************************************************************************************");
            System.out.println("Selected Function is comparison.");
            String plainText = FileReadWrite.ReadFile("plaintext.txt");
            System.out.println("Plain text in the file plaintext.txt: " + plainText);
            byte[] Encrypted = EncDecFunction.encryptCBC(plainText);
            System.out.println("\n1st Encrypted Text with CBC: " + DatatypeConverter.printHexBinary(Encrypted));
            Encrypted = EncDecFunction.encryptCBC(plainText);
            System.out.println("2nd Encrypted Text with CBC: " + DatatypeConverter.printHexBinary(Encrypted));
            Encrypted = EncDecFunction.encryptECB(plainText);
            System.out.println("\n1st Encrypted Text with ECB: "+ DatatypeConverter.printHexBinary(Encrypted));
            Encrypted = EncDecFunction.encryptECB(plainText);
            System.out.println("2nd Encrypted Text with ECB: "+ DatatypeConverter.printHexBinary(Encrypted));
            //System.out.println(EncDecFunction.decryptECB(Encrypted));
            System.out.println("********************************************************************************************************************\n");
        }
        //Read Current Files
        else if (option == 5) {
            ReadFiles();
        }
        //Exit the Program
        else {
            System.out.println("Terminating the program.");
            System.exit(0);
        }
    }
}
