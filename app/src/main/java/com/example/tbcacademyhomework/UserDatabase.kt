package com.example.tbcacademyhomework

object UserDatabase {

    private val users = mutableListOf(
        User(
            id = 1,
            firstName = "გრიშა",
            lastName = "ონიანი",
            birthday = "1724647601641",
            address = "სტალინის სახლმუზეუმი",
            email = "grisha@mail.ru"
        ),
        User(
            id = 2,
            firstName = "Jemal",
            lastName = "Kakauridze",
            birthday = "1714647601641",
            address = "თბილისი, ლილოს მიტოვებული ქარხანა",
            email = "jemal@gmail.com"
        ),
        User(
            id = 2,
            firstName = "Omger",
            lastName = "Kakauridze",
            birthday = "1724647701641",
            address = "თბილისი, ასათიანი 18",
            email = "omger@gmail.com"
        ),
        User(
            id = 32,
            firstName = "ბორის",
            lastName = "გარუჩავა",
            birthday = "1714947701641",
            address = "თბილისი, იაშვილი 14",
            email = ""
        ),
        User(
            id = 34,
            firstName = "აბთო",
            lastName = "სიხარულიძე",
            birthday = "1711947701641",
            address = "ფოთი",
            email = "tebzi@gmail.com",
            desc = null
        )
    )

    fun addUser(user: User): Response {
        if (userExists(user)) return Response.Error(R.string.user_already_exists)
        else {
            users.add(user)
            return Response.Success()
        }

    }

    private fun userExists(user: User): Boolean {
        return users.find { it.email == user.email } != null

    }

    fun getUser(field: String): User? {
        val user = with(users) {
            find {
                field.lowercase() == it.firstName.lowercase() || field.lowercase() == it.lastName.lowercase() || field.lowercase() == it.address || field.lowercase() == it.email.lowercase() || field.lowercase() == it.birthday.toLong()
                    .toFormattedDate()
                    .lowercase() || field.lowercase() == it.desc?.lowercase()
            }
        }
        return user
    }
}
