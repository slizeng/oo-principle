---
- GIVEN two parking lots A and B
- AND and the order of A is higher than B's
- AND both of them have available parking space
- WHEN parking boy parks this car
- THEN should park this card into A and return a ticket 

---
- GIVEN two parking lots A and B
- AND and the order of A is higher than B's
- AND A has not available parking space
- AND B has available parking space
- WHEN parking boy parks this car
- THEN should park this card into B and return a ticket 

---
- GIVEN two parking lots A and B
- AND and the order of A is higher than B's
- AND none of them have available parking space
- WHEN parking boy parks this car
- THEN should not park this car and no ticket returned.

---
- GIVEN a car parked in a parking lot
- WHEN parking boy picks this car with matched ticket
- THEN should return this car

---
- GIVEN a car parked in a parking lot
- WHEN parking boy picks this car without matched ticket
- THEN should not return this car