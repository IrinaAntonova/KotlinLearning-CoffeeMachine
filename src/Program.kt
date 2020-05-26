import java.util.*

fun main() {
    val coffeeMachine = CoffeeMachine()
    val scanner = Scanner(System.`in`)

    while (coffeeMachine.state != CoffeeMachineState.EXIT) {
        coffeeMachine.processInput(scanner.next())
    }
}
