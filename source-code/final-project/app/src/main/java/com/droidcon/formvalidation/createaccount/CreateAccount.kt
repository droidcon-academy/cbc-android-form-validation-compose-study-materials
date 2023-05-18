package com.droidcon.formvalidation.createaccount

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.droidcon.formvalidation.ui.theme.AppButton
import com.droidcon.formvalidation.ui.theme.AppTextField
import com.droidcon.formvalidation.ui.theme.AppTheme
import com.droidcon.formvalidation.R
import com.droidcon.formvalidation.createaccount.validation.ValidatorFactory
import java.util.*

@Composable
fun CreateAccountForm(paddingValues: PaddingValues, viewModel: CreateAccountViewModel) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    val year = calendar[Calendar.YEAR]
    val month = calendar[Calendar.MONTH]
    val dayOfMonth = calendar[Calendar.DAY_OF_MONTH]

    val state = viewModel.state.collectAsState()

    val datePicker = DatePickerDialog(
        context,
        { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDayOfMonth: Int ->
            viewModel.onEvent(CreateAccountEvent.DateOfBirthChanged("$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"))
        },
        year, month, dayOfMonth
    )

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .padding(16.dp)
    ) {

        AppTextField(
            value = state.value.name,
            hint = stringResource(R.string.name),
            leadingIcon = Icons.Filled.Person,
            error = state.value.nameError,
            onValueChanged = { viewModel.onEvent(CreateAccountEvent.NameChanged(it)) }
        )

        AppTextField(
            value = state.value.email,
            hint = stringResource(R.string.email),
            leadingIcon = Icons.Filled.Email,
            error = state.value.emailError,
            onValueChanged = { viewModel.onEvent(CreateAccountEvent.EmailChanged(it)) }
        )

        AppTextField(
            value = state.value.password,
            hint = stringResource(R.string.password),
            leadingIcon = Icons.Filled.Lock,
            isPasswordField = true,
            error = state.value.passwordError,
            onValueChanged = { viewModel.onEvent(CreateAccountEvent.PasswordChanged(it)) }
        )

        AppTextField(
            value = state.value.dateOfBirth,
            hint = stringResource(R.string.date_of_birth),
            leadingIcon = Icons.Filled.CalendarMonth,
            isClickOnly = true,
            onClick = {
                datePicker.show()
            },
            error = state.value.dateOfBirthError,
            onValueChanged = { viewModel.onEvent(CreateAccountEvent.DateOfBirthChanged(it)) }
        )

        Spacer(modifier = Modifier.size(16.dp))

        AppButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.create_account)
        ) {
            viewModel.onEvent(CreateAccountEvent.CreateAccount)
        }
    }
}

@Preview
@Composable
fun CreateAccountFormPreview() {
    AppTheme {
        CreateAccountForm(
            paddingValues = PaddingValues(4.dp),
            viewModel = CreateAccountViewModel(ValidatorFactory.create())
        )
    }
}