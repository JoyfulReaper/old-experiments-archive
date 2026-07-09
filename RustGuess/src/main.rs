use rand::Rng;
use std::io;
use std::io::Write;

fn main() {
    intro();

    let mut rng = rand::thread_rng(); 
    let magic_number = rng.gen_range(0..=100);

    for n in 1..11 {
        let mut guess_input = String::new();

        print!("Guess a number between 0 and 100: ");
        io::stdout().flush().unwrap();

        io::stdin()
            .read_line(&mut guess_input)
            .expect("Failed to read input.");

        let guess = guess_input.trim().parse::<i64>()
            .expect("Input must be a 32 bit integer");

        if guess > magic_number {
            println!("Your guess was too high!\n");
        } else if guess < magic_number {
            println!("Your guess was too low!\n");
        }
        else {
            println!("Your guess was just right! You win!\n");
            break;
        }

        if is_game_over(n)
        {
            println!("You weren't able to guess the number, you lose!");
            break;
        }
    }

    
}

fn intro() {
    println!("Rust Guess by JoyfulReaper (C) 2022. MIT Licensed\n\n");
}

fn is_game_over(turn: i64) -> bool
{
    if turn >= 10
    {
        return true;
    }

    return false;
}