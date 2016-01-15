# BankSys ([![Build Status](https://travis-ci.org/lincolnrocha/BankSys.svg)](https://travis-ci.org/lincolnrocha /BankSys))

BankSys is a simple Java system, for academic purposes, used as a tool example in check correctness of ArcatchJ.

<img src="https://cloud.githubusercontent.com/assets/3067971/12343559/7d61345a-bb13-11e5-96ac-b97b485eef62.png"/>

BankSys...

 * Simulates the main banking transactions
    * Credit
    * Debit
    * Transfer
    * Balance
 * Have proper care of exception handling
    * Raising
    * Signaling
    * Handling
    * Propagation
    * Remapping
 * Uses ArCatchJ to check the architectural conformation of exception handling
   * Mapping between the architectural entities and implementing entities
   * Has 34 defined design rules
 * Available in 3 versions
   * A correct and two other with input errors

Contemplated Rules
-------------------
 * Context
    * ID - Rule - Total
    * M = Module
    * E = Exception
  
  
1. R01 - only M can-raise E - 4 <br/>
<code>only(account).canRaise(accountExceptions)</code><br/>
<code>only(account).canRaise(accountExceptions)</code><br/>
<code>only(persistence).canRaise(persistenceExceptions)</code><br/>
<code>only(controller).canRaise(controllerExceptions)</code><br/>


2. R02 - M can-raise-only E - 1 <br/>
<code>module(controller).canRaiseOnly(controllerExceptions)</code><br/>


3. R03 - M cannot-raise E - 1 <br/>
<code>module(controller).cannotRaise(accountExceptions)</code><br/>


4. R04 - M must-raise E -1 <br/>
<code>module(controller).mustRaise(controllerExceptions)</code><br/>


5. R05 - only M can-signal E - 3 <br/>
<code>only(account).canSignal(accountExceptions)</code><br/>
<code>only(persistence).canSignal(persistenceExceptions)</code><br/>
<code>only(controller).canSignal(controllerExceptions)</code><br/>


6. R06 - M can-signal-only E - 1 <br/>
<code>module(controller).canSignalOnly(controllerExceptions)</code><br/>


7. R07 - M cannot-signal E - 1 <br/>
<code>module(controller).cannotSignal(accountExceptions)</code><br/>

8. R08 - M must-signal E - 0 <br/>


9. R09 - only M can-handle E - 1 <br/>
<code>only(controller).canHandle(persistenceExceptions)</code><br/>


10. R10 - M can-handle-only E - 2 <br/>
<code>module(view).canHandleOnly(controllerExceptions)</code><br/>
<code>module(persistence).canHandleOnly(accountExceptions)</code><br/>


11. R11 - M cannot-handle E - 2 <br/>
<code>module(persistence).cannotHandle(persistenceExceptions)</code><br/>
<code>module(controller).cannotHandle(controllerExceptions)</code><br/>


12. R12 - M must-handle E - 1 <br/>
<code>module(controller).mustHandle(persistenceExceptions)</code><br/>


13. R13 - only M can-signal E to N - 10 <br/>
<code>only(account).canSignal(accountExceptions).to(controller)</code><br/>
<code>only(account).canSignal(accountExceptions).to(persistence)</code><br/>
<code>only(persistence).canSignal(persistenceExceptions).to(controller)</code><br/>
<code>only(account).canSignal(accountExceptions).to(controller)</code><br/>
<code>only(persistence).canSignal(persistenceExceptions).to(account)</code><br/>
<code>only(controller).canSignal(controllerExceptions).to(persistence)</code><br/>
<code>only(controller).canSignal(controllerExceptions).to(account)</code><br/>
<code>only(controller).canSignal(controllerExceptions).to(view)</code><br/>
<code>only(persistence).canSignal(persistenceExceptions).to(view)</code><br/>
<code>only(account).canSignal(accountExceptions).to(view)</code><br/>


14. R14 - M can-signal-only E to N - 4<br/>
<code>module(controller).canSignalOnly(controllerExceptions).to(view)</code><br/>
<code>module(account).canSignalOnly(accountExceptions).to(controller)</code><br/>
<code>module(persistence).canSignalOnly(persistenceExceptions).to(account)</code><br/>
<code>module(persistence).canSignalOnly(persistenceExceptions).to(controller)</code><br/>


15. R15 - M cannot-signal E to N - 1 <br/>
<code>module(controller).cannotSignal(controllerExceptions).to(account)</code><br/>


16. R16 - M must-signal E - 1 <br/> 
<code>module(controller).mustSignal(controllerExceptions)</code><br/>

Checking the Conformation Architectural
-------------------

1. Set in build.xml the version to be used:<br/>
<code>project name="BankSys"...</code><br/>

2. Run the build.xml<br/>

3. View the report generated in the subdirectory build/report:<br/>

Running the Project
-------------------

1. Run the ATM24H.java


