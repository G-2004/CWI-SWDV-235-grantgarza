@param String title
@param edu.cwi.UserAccount account

@template.accountLayout(
    title = title,
    account = @template.userAccount(
        name = account.name(),
        balance = account.formatBalance()
    ),
    detail = @template.transactionsList(
        transactions = account.transactions()
    )
)