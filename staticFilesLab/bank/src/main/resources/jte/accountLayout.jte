@import gg.jte.Content

@param String title
@param Content account
@param Content detail

<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>${title}</title>

        <link rel="stylesheet" href="css/universal.css">
    </head>
    <body>
        <div class="container">
            <div class="row align-items-start">
                <div class="col">
                    ${account}
                </div>
                <div class="col">
                    ${detail}
                </div>
            </div>
        </div>
    </body>
</html>