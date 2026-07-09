package main

import (
	"fmt"
	"os"
	"bufio"
	"math/rand"
	"strconv"
	"time"
)

func main() {
	scanner := bufio.NewScanner(os.Stdin)

	rand.Seed(time.Now().UnixNano())
	target := rand.Intn(100)

	for i := 0; i < 10; i++ {
		fmt.Print("Enter your guess (0 - 100): ");

		scanner.Scan()
		guessString := scanner.Text()
		guess, _ := strconv.Atoi(guessString)

		if guess > target {
			fmt.Println("Too High")
		} else if guess < target {
			fmt.Println("To Low")
		} else {
			fmt.Println("You win!")
			break
		}
	}
}