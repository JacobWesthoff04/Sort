public class sort {

    public static void main(String[] args) {
        // Validate the number of arguments
        if (args.length != 5) {
            System.out.println("Error: Please provide exactly five integer arguments.");
            return;
        }

        int[] numbers = new int[5];
        try {
            // Convert command-line arguments to integers
            for (int i = 0; i < 5; i++) {
                numbers[i] = Integer.parseInt(args[i]);
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: All arguments must be valid integers.");
            return;
        }

        // Copy array for sorting
        int[] sortedNumbers = numbers.clone();

        // Sort using Bubble Sort
        bubbleSort(sortedNumbers);

        // Define threads
        Thread unsortedThread = new Thread(() -> {
            System.out.print("Unsorted Numbers: ");
            for (int num : numbers) {
                System.out.print(num + " ");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println();
        });

        Thread sortedThread = new Thread(() -> {
            System.out.print("Sorted Numbers: ");
            for (int num : sortedNumbers) {
                System.out.print(num + " ");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println();
        });

        // Start threads
        unsortedThread.start();
        sortedThread.start();

        // Wait for both threads to finish
        try {
            unsortedThread.join();
            sortedThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Calculate and print the sum
        int sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        System.out.println("Sum of Numbers: " + sum);
    }

    // Bubble Sort Implementation
    private static void bubbleSort(int[] arr) {
        int n = arr.length;
        boolean swapped;
        do {
            swapped = false;
            for (int i = 1; i < n; i++) {
                if (arr[i - 1] > arr[i]) {
                    int temp = arr[i - 1];
                    arr[i - 1] = arr[i];
                    arr[i] = temp;
                    swapped = true;
                }
            }
            n--;
        } while (swapped);
    }
}
