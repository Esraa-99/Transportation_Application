--
-- Database: `transportation`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--
create database transportation;
CREATE TABLE `admin` (
  `ID` int NOT NULL,
  `username` varchar(100) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `password` varchar(50) NOT NULL,
  `Mobile` varchar(12) NOT NULL
) ;

-- --------------------------------------------------------

--
-- Table structure for table `drivers`
--

CREATE TABLE `drivers` (
  `Username` varchar(100) NOT NULL,
  `Mobile` varchar(12) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `Password` varchar(50) NOT NULL,
  `National_ID` varchar(14) NOT NULL,
  `driving_licence` varchar(100) NOT NULL,
  `ID` int NOT NULL,
  `Status` varchar(10) NOT NULL
) ;

-- --------------------------------------------------------

--
-- Table structure for table `favourite_areas`
--

CREATE TABLE `favourite_areas` (
  `ID` int NOT NULL,
  `Location` varchar(100) NOT NULL,
  `Driver_ID` int NOT NULL
);

-- --------------------------------------------------------

--
-- Table structure for table `trip`
--

CREATE TABLE `trip` (
  `ID` int NOT NULL,
  `source` varchar(100) NOT NULL,
  `destination` varchar(100) NOT NULL,
  `rate` int NOT NULL,
  `user_id` int NOT NULL,
  `driver_id` int NOT NULL
);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `Username` varchar(100) NOT NULL,
  `Mobile` varchar(12) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `password` varchar(50) NOT NULL,
  `ID` int NOT NULL
);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `drivers`
--
ALTER TABLE `drivers`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `National_ID` (`National_ID`);

--
-- Indexes for table `favourite_areas`
--
ALTER TABLE `favourite_areas`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_DriverID_Location` (`Driver_ID`);

--
-- Indexes for table `trip`
--
ALTER TABLE `trip`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_Trip_Driver` (`driver_id`),
  ADD KEY `FK_Trip_User` (`user_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `ID` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `drivers`
--
ALTER TABLE `drivers`
  MODIFY `ID` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `favourite_areas`
--
ALTER TABLE `favourite_areas`
  MODIFY `ID` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `trip`
--
ALTER TABLE `trip`
  MODIFY `ID` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `ID` int NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `favourite_areas`
--
ALTER TABLE `favourite_areas`
  ADD CONSTRAINT `FK_DriverID_Location` FOREIGN KEY (`Driver_ID`) REFERENCES `drivers` (`ID`);

--
-- Constraints for table `trip`
--
ALTER TABLE `trip`
  ADD CONSTRAINT `FK_Trip_Driver` FOREIGN KEY (`driver_id`) REFERENCES `drivers` (`ID`),
  ADD CONSTRAINT `FK_Trip_User` FOREIGN KEY (`user_id`) REFERENCES `users` (`ID`);
