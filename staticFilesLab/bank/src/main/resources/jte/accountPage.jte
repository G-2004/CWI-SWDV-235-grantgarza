
@param String title
@param edu.cwi.UserAccount account

@template.jte.accountLayout(
    title = title,
    account = @`@template.jte.userAccount(account.name(), account.formatBalance(), account.id())`,
    detail = @`
        @template.jte.transactionsList(account.transactions())

        <h2>New Transaction</h2>

        <form action="/account/${account.id()}/transactions" method="post">
            <div>
                <label for="transactionType">Transaction Type</label>
                <select name="transactionType" id="transactionType">
                    <option value="DEPOSIT">Deposit</option>
                    <option value="WITHDRAWAL">Withdrawal</option>
                </select>
            </div>

            <div>
                <label for="amount">Amount</label>
                <input type="number" name="amount" id="amount"
                    step="0.01" min="0.01" required>
            </div>

            <div>
                <label for="description">Description</label>
                <input type="text" name="description" id="description">
            </div>

            <button type="submit">Submit Transaction</button>
        </form>
    `
)
