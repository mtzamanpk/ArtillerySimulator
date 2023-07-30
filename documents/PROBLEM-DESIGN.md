# Problem Specifications

### Define the Problem

The problem is to design a Howitzer firing simulator in order to train soldiers on the kinematics and dynamics of it firing a projectile across an empty field.

### Do Background Research:

A Howitzer is a artillery weapon commonly used in battery formations, it is characterized by its shorter barrel and arched projectile trajectory. It is like a cross between cannon and a gun. Weighing around 15,000lbs, the shells can reach a speed of up to 1600 m/s. It can fire at both high and low angles having a range of 0&deg;-90&deg; making it very versatile. The average shell is 95 lbs and 60 cm in length. The physics of the projectile can be described by rigid body kinematics and these properties will form the basis of our simulator. We are going to model ours after the M777 Howitzer.

### Rigid Body Kinematics:

Kinematics is a sub field of physics that helps us describe the motion of objects in the case a rigid body.

#### Formulas:

<b>Formula for any object expressed in an interior frame:</b>

p(t) = v(t)

mv(t) = f(t) + f<sub>d</sub>(t) + mg

<b>Formula for the drag force: </b>

f<sub>d</sub>(t) = -1/2CpAu(t)<sup>2</sup>

# Design Requirements

### Properties / Objectives:
- Write a program that simulates a Howitzer in the programming language of Java.
- The Howitzer simulation should be model behavior as best as possible; with correct speed, weight, angles of rotation, barrel height and so on. 
- The program's physic logic should simulate in a 3D plane, with 3 dimensions of measure. 
- We want high accuracy; therefore must use a modified version of Euler's Approximation to map out the flight path.
- Have the ability to input different values for the variables to get different results.

### Constraints:
1. Economic Factors:
    - Cost of Project: This <u><b>project does not have a budget</b></u> therefore we are using all open source software. There is a cost in the sense of labour required by project contributors as well as any other supplies used by contributors to complete the project; but this will not be realized. 
    - Cost of Use: The equipment that will be used for development will include personal computers along with lab computers; both of these have already been purchased and do not warrant a cost of use; our repo location (GitLab) is also provided free of charge; this unfortunately results in a <u><b>lack of DevOps features.</b></u>
    - <b>How It Impacts Our Project:</b> Since there is no budget; we are unable to do real tests in the field to compare our simulator to a real world howitzer - we just need to trust sources that can provide information online. A lack of dev ops features also resulted in a simpler MVP as compared to what was possible in the same time frame - we lost out on efficiency throughout the project.
    <br><br/>
2. Social Factors: 
    - Time is a constraint when it comes to this project as contributors have around 2 months to complete it, therefore the <u><b>scope should be kept small</b></u> enough so that project can be completed.
    - <b>How It Impacts Our Project:</b> Due to the time constraint we will need to use easier forms of testing; for example, in boundary value testing normal robust will suffice; there is no need to waste time on a worst case robust method - another example is for graph testing; we will use node or path coverage instead of prime path coverage to simplify and therefore speed up our testing. 
    <br><br/>

3. Reliability
    - Project has to be very reliable initially, but factors such a features falling out of support will decrease the reliability long term, this can be mitigated.  Therefore project should be <u><b>reliable for the short term</b></u>.
    - <b>How It Impacts Our Project:</b> Our projects reliability should be 99%; this means that we should be only within 1% of error for final position of the projectile - the reason for this is a simulator is meant to educate soldiers on correct howitzer positioning; if it is even slightly off over 25km of distance that small mistake can translate to hundreds of meters. 1% is just for the MVP; if we are to continue working on this project we would ideally want the constraint to be under 0.01%.
    <br><br/>

4. Sustainability and Environmental Factors 
    - The project must be completed using technology which currently is not very sustainable so project should strive to <u><b>use sustainable alternative where possible</b></u>.
    - <b>How It Impacts Our Project:</b> The constraint is our simulator must be better for the environment / more sustainable than live practice - this constraint is easily achieved since we will not be using up shells and destroying the environment with explosives while training; it's all simulated with relatively low electric power loss (low processing power).
    <br><br/>

 5. Societal Impacts
    - The project should have <u><b>minimal societal impact </b></u>other then to help train soldiers or anyone else who would like to know how Howitzers work. The simulator has very limited other real world applications.
    - <b>How It Impacts Our Project:</b> This is a more fluid constraint; we believe that ethically we need to allow humans to decide on positioning in the real world; the best way we can support the societal impacts is by constraining the software to be as accurate as possible; and more importantly to mimic real-world howitzer configuration as best as possible - with barrel pose alongside angles; we are also limiting the vertical angle to 85 degrees to avoid soldiers thinking shooting directly is acceptable.
    <br><br/>  

### Simulator Variables
- barrel pose 
- radius and mass of the projectile
- drag coefficient 
- force applied to the projectile 
- initial speed
- gravity




