@param java.util.Map<String, String> transactions

        <ul>
            @for(var transaction : transactions.entrySet())
                <li>${transaction.getKey()}: ${transaction.getValue()}</li>
            @endfor
        </ul>