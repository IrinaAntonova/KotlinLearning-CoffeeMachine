class CoffeeMachine {
    var state: CoffeeMachineState = CoffeeMachineState.WAITING
    private var water = 400
    private var milk = 540
    private var beans = 120
    private var cups = 9
    private var money = 550
    fun processInput(input: String) {
        when (state) {
            CoffeeMachineState.WAITING -> chooseNextAction(input)
            CoffeeMachineState.BUY -> chooseDrink(input)
            CoffeeMachineState.ADD_WATER -> {
                water += input.toInt()
                state = CoffeeMachineState.ADD_MILK
                println("Write how many ml of milk do you want to add: ")
            }
            CoffeeMachineState.ADD_MILK -> {
                milk += input.toInt()
                state = CoffeeMachineState.ADD_BEANS
                println("Write how many grams of coffee beans do you want to add: ")
            }
            CoffeeMachineState.ADD_BEANS -> {
                beans += input.toInt()
                state = CoffeeMachineState.ADD_CUPS
                println("Write how many disposable cups of coffee do you want to add: ")
            }
            CoffeeMachineState.ADD_CUPS -> {
                cups += input.toInt()
                println()
                writeAction()
            }
            else -> writeAction()
        }
    }

    init {
        writeAction()
    }

    private fun chooseNextAction(action: String) {
        when (action) {
            "buy" -> buy()
            "fill" -> fill()
            "take" -> take()
            "remaining" -> showState()
            "exit" -> {
                state = CoffeeMachineState.EXIT
            }
        }
    }

    private fun writeAction() {
        state = CoffeeMachineState.WAITING
        println("Write action (buy, fill, take, remaining, exit): ")
        println()
    }

    private fun buy() {
        state = CoffeeMachineState.BUY
        println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino: ")
    }

    private fun chooseDrink(drinkIndex: String) {
        when (drinkIndex) {
            "1" -> buyDrink(250, 0, 16, 4)
            "2" -> buyDrink(350, 75, 20, 7)
            "3" -> buyDrink(200, 100, 12, 6)
            "back" -> writeAction()
        }
    }

    private fun buyDrink(_water: Int, _milk: Int, _beans: Int, _money: Int) {
        var notEnough = ""
        when {
            _water > water -> notEnough = "water"
            _milk > milk -> notEnough = "milk"
            _beans > beans -> notEnough = "beans"
            cups == 0 -> notEnough = "cups"
            else -> {
                println("I have enough resources, making you a coffee!")
                water -= _water
                milk -= _milk
                beans -= _beans
                money += _money
                cups--
            }
        }
        if (notEnough.isNotEmpty())
            println("Sorry, not enough $notEnough!")
        println()
        writeAction()
    }

    private fun fill() {
        state = CoffeeMachineState.ADD_WATER
        println("Write how many ml of water do you want to add: ")
    }

    private fun take() {
        println("I gave you \$$money")
        money = 0
        println()
        writeAction()
    }

    private fun showState() {
        println("The coffee machine has:")
        println("$water of water")
        println("$milk of milk")
        println("$beans of coffee beans")
        println("$cups of disposable cups")
        println("$money of money")
        println()

        writeAction()
    }
}