## `parking manager` Cases

###
- **GIVEN** a `parking manager`
- **AND** `parking manager` has available parking space
- **WHEN** `parking manager` parks a car by itself 
- **THEN** return a `ticket`

###
- **GIVEN** a `parking manager`
- **AND** `parking manager` has **NOT** available parking space
- **WHEN** `parking manager` parks a car by itself 
- **THEN** return a `ticket`

###
- **GIVEN** a `parking manager`
- **AND** a `car` parked in manager's park space
- **WHEN** `parking manager` picks car with a valid ticket 
- **THEN** return target car

###
- **GIVEN** a `parking manager`
- **AND** a `car` parked in manager's park space
- **WHEN** `parking manager` picks car with an invalid ticket 
- **THEN** no car will be returned

###
- **GIVEN** a `parking manager` managing a `graduate parking boy` and a `smart parking boy`
- **AND** both `graduate parking boy` and `smart parking boy` currently are able to park only one car
- **AND** `parking manager` has not available parking space
- **AND** 2 `cars` need to be parked
- **WHEN** `parking manager` manages to park those 2 `cars`
- **THEN** 1 `car` will be parked by `graduate parking boy` and return a `ticket`
- **AND** another 1 `car` will be parked by `smart parking boy` and return a `ticket`

###
- **GIVEN** a `parking manager` managing a `graduate parking boy`
- **AND** `graduate parking boy` has 1 available parking space  
- **AND** `parking manager` has 1 available parking space
- **AND** 2 `cars` need to be parked
- **WHEN** `parking manager` manages to park the `car`
- **THEN** one `car` will be parked by `graduate parking boy` and return a `ticket`
- **AND** another `car` will be parked by `parking manager` and return a `ticket`

###
- **GIVEN** a `parking manager` managing a `graduate parking boy`
- **AND** `graduate parking boy` has no available parking space  
- **AND** `parking manager` has 1 available parking space
- **AND** 1 `car` need to be parked
- **WHEN** `parking manager` manages to park the `car`
- **AND** the `car` will be parked by `parking manager` and return a `ticket`

###
- **GIVEN** a `parking manager` managing a `graduate parking boy`
- **AND** `graduate parking boy` has no available parking space  
- **AND** `parking manager` has no available parking space
- **AND** 1 `car` need to be parked
- **WHEN** `parking manager` manages to park the `car`
- **AND** the `car` will NOT be parked

###
- **GIVEN** a `parking manager` managing a `graduate parking boy`
- **AND** 1 `car` is parked in `graduate parking boy`'s parking lot
- **WHEN** `parking manager` asks `graduate parking boy` to pick this car with valid `ticket`
- **AND** the `car` will be returned

###
- **GIVEN** a `parking manager` managing a `graduate parking boy`
- **AND** 1 `car` is parked in `graduate parking boy`'s parking lot
- **WHEN** `parking manager` manages itself to pick this car with valid `ticket`
- **AND** the `car` will be returned

###
- **GIVEN** a `parking manager` managing a `graduate parking boy` and a `smart parking boy`
- **AND** 1 `car` is parked in `graduate parking boy`'s parking lot
- **WHEN** `parking manager` manages `smart parking boy` to pick this car with valid `ticket`
- **AND** the `car` will NOT be returned

###
- **GIVEN** a `parking manager` managing a `graduate parking boy`
- **AND** 1 `car` is parked in any parking space
- **WHEN** `parking manager` manages `graduate parking boy` to pick the `car` with invalid `ticket`
- **AND** the `car` will NOT be returned


