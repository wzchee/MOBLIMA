/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.moblima;
import java.util.Scanner;
/**
 *
 * @author Chee Wen Zhan
 */
public class MOBLIMA {

    public static void main(String[] args) {
        System.out.println("Welcome to MOBLIMA! Please login to use our services.");
        System.out.println("Are you logging in as USER or STAFF?");
        System.out.println("1. User");
        System.out.println("2. Staff");
        System.out.println("3. Create a new account");
        System.out.print("Please enter your choice here: ");
        
        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();
        
        String email, password;
        switch(choice){
            case 1:
                System.out.println("\nWelcome STAFF!");
                System.out.print("Enter your email address: ");
                email = input.next();
                System.out.print("Enter your password: ");
                password = input.next();
                
                if(email.equals("admin") && password.equals("admin")){
                    Staff.main(["admin"]);
                }
                break;
            case 2:
                break;
            case 3:
                
                break;
            default:
        }
        
    }
}
