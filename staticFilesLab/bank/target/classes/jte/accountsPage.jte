@param String title
@param edu.cwi.User user
@param java.util.List<edu.cwi.Account> accounts

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>${title}</title>
    <link rel="stylesheet" href="css/universal.css">
</head>
<body>
    <div class="container">
        <h1>Accounts</h1>

        <p>Welcome, ${user.getFirstName()} ${user.getLastName()}</p>

        <a href="/logout">Log Out</a>

        <h2>Your Accounts</h2>

        <ul>
            @for (var account : accounts)
                <li>
                    <strong>${account.getAccountType()}</strong>
                    - $${account.getBalance()}
                    <a href="/account/${account.getId()}">View Account</a>
                </li>
            @else
                <li>You have no accounts.</li>
            @endfor
        </ul>

    </div>
</body>
</html>