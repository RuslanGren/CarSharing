# Car-sharing

## Details
Application that manages a car-sharing service allowing companies to rent out their cars and find customers. As a manager you can create companies and for each company
you can add cars for sharing. As a customer you can rent a car and return a rented car. If there are no available cars in the company you can't rent them. All information is stored in H2 database and update after each operation.

## Example
Manager functions:
```
1. Log in as a manager
2. Log in as a customer
3. Create a customer
0. Exit
> 1

1. Company list
2. Create a company
0. Back
> 2

Enter the company name:
> Car To Go
The company was created!

1. Company list
2. Create a company
0. Back
> 2

Enter the company name:
> Drive Now
The company was created!

1. Company list
2. Create a company
0. Back
> 1

Choose the company:
1. Car To Go
2. Drive Now
0. Back
> 1

'Car To Go' company
1. Car list
2. Create a car
0. Back
> 1

The car list is empty!

1. Car list
2. Create a car
0. Back
> 2

Enter the car name:
> Hyundai Venue
The car was added!

1. Car list
2. Create a car
0. Back
> 2

Enter the car name:
> Maruti Suzuki Dzire
The car was added!

1. Car list
2. Create a car
0. Back
> 1

Car list:
1. Hyundai Venue
2. Maruti Suzuki Dzire

1. Car list
2. Create a car
0. Back
> 0

1. Company list
2. Create a company
0. Back
> 1

Choose the company:
1. Car To Go
2. Drive Now
0. Back
> 2

'Drive Now' company
1. Car list
2. Create a car
0. Back
> 1

The car list is empty!

1. Car list
2. Create a car
0. Back
> 2

Enter the car name:
> Lamborghini Urraco
The car was added!

1. Car list
2. Create a car
0. Back
> 1

Car list:
1. Lamborghini Urraco

1. Car list
2. Create a car
0. Back
> 0

1. Company list
2. Create a company
0. Back
> 0

1. Log in as a manager
2. Log in as a customer
3. Create a customer
0. Exit
> 0
```
Customer functions:
```
1. Log in as a manager
2. Log in as a customer
3. Create a customer
0. Exit
> 2

The customer list is empty!

1. Log in as a manager
2. Log in as a customer
3. Create a customer
0. Exit
> 3

Enter the customer name:
> First customer
The customer was added!

1. Log in as a manager
2. Log in as a customer
3. Create a customer
0. Exit
> 3

Enter the customer name:
> Second customer
The customer was added!

1. Log in as a manager
2. Log in as a customer
3. Create a customer
0. Exit
> 2

Customer list:
1. First customer
2. Second customer
0. Back
> 1

1. Rent a car
2. Return a rented car
3. My rented car
0. Back
> 3

You didn't rent a car!

1. Rent a car
2. Return a rented car
3. My rented car
0. Back
> 2

You didn't rent a car!

1. Rent a car
2. Return a rented car
3. My rented car
0. Back
> 0

1. Log in as a manager
2. Log in as a customer
3. Create a customer
0. Exit
> 0
```
```
1. Log in as a manager
2. Log in as a customer
3. Create a customer
0. Exit
> 2

The customer list:
1. First customer
2. Second customer
0. Back
> 1

1. Rent a car
2. Return a rented car
3. My rented car
0. Back
> 1

Choose a company:
1. Car To Go
2. Drive Now
0. Back
> 1

Choose a car:
1. Hyundai Venue
2. Maruti Suzuki Dzire
0. Back
> 1

You rented 'Hyundai Venue'

1. Rent a car
2. Return a rented car
3. My rented car
0. Back
> 3

Your rented car:
Hyundai Venue
Company:
Car To Go

1. Rent a car
2. Return a rented car
3. My rented car
0. Back
> 1

You've already rented a car!

1. Rent a car
2. Return a rented car
3. My rented car
0. Back
> 2

You've returned a rented car!

1. Rent a car
2. Return a rented car
3. My rented car
0. Back
> 0

1. Log in as a manager
2. Log in as a customer
3. Create a customer
0. Exit
> 0
```
