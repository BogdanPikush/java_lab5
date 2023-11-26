# Клас Bank
Клас Bank представляє просту банківську систему з можливістю створювати рахунки, знаходити їх за номером та переказувати кошти між ними. Обробка помилок включає винятки для від'ємного балансу та відсутності рахунків.

# Методи
## createAccount
- Створює рахунок із заданим ім'ям та початковим депозитом.
- Виняток NegativeAmountException при від'ємному депозиті.
## findAccount
- Знаходить рахунок за номером.
- Виняток AccountNotFoundException при відсутності рахунку.
## transferMoney
- Переказ коштів між рахунками.
- Винятки: NegativeAmountException, InsufficientFundsException, AccountNotFoundException.

# Клас BankAccount
Клас BankAccount представляє рахунок з можливістю внесення та зняття коштів. Обробка помилок включає виняток для від'ємних сум та недостатньої кількості коштів.

# Методи
## deposit
- Вносить гроші на рахунок.
- Виняток NegativeAmountException при від'ємному внеску.
## withdraw
- Знімає гроші з рахунку.
- Винятки: NegativeAmountException, InsufficientFundsException.
## getAccountSummary
- Повертає інформацію про рахунок у форматі рядка.

# Клас BankAccountTest
Клас BankAccountTest містить тести для класу BankAccount, включаючи позитивні та негативні сценарії.

# Клас BankTest
Клас BankTest містить тести для класу Bank, охоплюючи створення рахунків, пошук та переказ коштів.
