According to rubric in this phase we need to have: 

• Multiple design concepts are considered by applying formal decision-making methods to assist in choosing between alternative 
conceptual designs iteratively, and a novel solution is prompted from it 

• Metrics for design selection are clear and aligned with requirements and constraints. The choice of metrics is also justified. 

• Data is used after proposer investigation to support design selection objectively

Possible solutions:

1. one solution can be only having acceleration = m*g while other forces are constant (assume fired in vacuum).

2. 2nd solution, keep acceleration as it is in sol 1, now implement the wind component.

3. 3rd solution, now has acceleration and drag from sol 2, but also self propelled shell capabilities

Design selection methods:

Decision Matrix:

Weighted Decision Matrix:

Risk Assessment:

|-------------|-------------------|---------------|-------------|----------------|------------------|-------------------|---------|--------|          
|  Solutions  | Economic Factors  | Social Factors| Reliability | Sustainability | Societal Impacts | Variable Coverage | Accuracy| Totals |
|-------------|-------------------|---------------|-------------|----------------|------------------|-------------------|---------|--------|  
|  Solution 1 | 5                 | 4             | 2           | 4              | 5                | 1                 | 1       | 22     |
|  Solution 2 | 5                 | 4             | 4           | 4              | 5                | 3                 | 3       | 26     |
|  Solution 3 | 5                 | 3             | 5           | 4              | 5                | 5                 | 5       | 31     |      


Justification of Decision Matrix:

Requirements were pulled from PROBLEM-DESIGN.md , the 5 plus how much each solutions covers all of the variables, plus how 
accurate of a simulator the solution would make. We felt these requirements best encompassed the project constraints and requirements, 
the 5 constraints re important to consider during the design phase which is why they are included in the matrix, and the variable 
coverage and accuracy are important to consider in the scope of this project. From the decision matrix we will go with solution #3 
as it has the highest score. 

Post Design Solution Analysis:

Comparing against real-world information provided by the manufacturer of M777 we can cearly
say that Solution 3 is the most accurate due to the max range, and max height being almost
identical to that we would expect in the real world. (max range of about 25km, and a max height of about 12km based on angled of 45 and 30.) Other solutions tended to be further off by factors of kilometers. 

Prototype:
Input: Angle from ground, angle from centre, the external force, mass

Output: x, y, z coordinates of projectile and maybe the path it took?
functions:
density function
main calculation function
optional formatting function for output/input
