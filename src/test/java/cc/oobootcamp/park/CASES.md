- GIVEN a park,
- AND the park has left parking spaces,
- AND a car with plate number which is not in the park,
- WHEN a car arrives and wants to park in,
- THEN should return succeed and a ticket with plate number,

- GIVEN a park,
- AND the park has not left parking spaces,
- AND a car with plate number which is not in the park,
- WHEN a car arrives and wants to park in,
- THEN should return failed without a ticket.

- GIVEN a park,
- AND a car with plate number which is in the park,
- WHEN a car leaves and provides a matched ticket with plate number,
- THEN should allow this car to leave.

- GIVEN a park,
- AND a car with plate number which is in the park,
- WHEN a car leaves but provides a mismatched ticket with plate number,
- THEN should not allow this car to leave.

- GIVEN a park,
- AND a car with plate number which is not in the park,
- WHEN a car parks successfully,
- THEN the number of park's available  parking spaces should minus one.

- GIVEN a park,
- AND a car with plate number which is in the park,
- WHEN a car leaves successfully,
- THEN the number of park's available parking spaces should plus one.