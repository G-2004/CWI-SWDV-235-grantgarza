@param java.util.List<String> transactions

<!DOCTYPE html>
<html>
<head>
    <title>Transactions</title>
</head>
<body>
    <h1>Transactions</h1>

    <form method="post" action="/transactions">
        <input type="text" name="transaction">
        <button type="submit">Add Transaction</button>
    </form>

    @for (int i = 0; i < transactions.size(); i++)
        <p>ID: ${i} - ${transactions.get(i)}</p>
    @endfor

    <h2>Update Transaction</h2>

    <form method="post" action="/transactions/update">
        <input type="number" name="id">
        <input type="text" name="transaction">
        <button type="submit">Update</button>
    </form>

    <h2>Delete Transaction</h2>

    <form method="post" action="/transactions/delete">
        <input type="number" name="id">
        <button type="submit">Delete</button>
    </form>
</body>
</html>