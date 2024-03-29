/* Deze database was oorspronkelijk voor een ander groepje bedoelt, echter hebben wij op advies van Erik Kuipers, deze nu overgenomen om op afstand met de database te kunnen werken.
Wij hebben de database helemaal leeggehaald en vervangen met onze eigen data (tabellen, diagrammen, FKs, PKs, testdata, etc.) Het enige wat over is gebleven is de naam van de database, omdat wij geen toegang hebben deze te veranderen.*/


USE [master]
GO
/****** Object:  Database [CodeCademyGroepB3]    Script Date: 29/03/2024 23:35:05 ******/
CREATE DATABASE [CodeCademyGroepB3]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'CodeCademyGroepB3', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.STUDENTEN\MSSQL\DATA\CodeCademyGroepB3.mdf' , SIZE = 73728KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'CodeCademyGroepB3_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.STUDENTEN\MSSQL\DATA\CodeCademyGroepB3_log.ldf' , SIZE = 73728KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
GO
ALTER DATABASE [CodeCademyGroepB3] SET COMPATIBILITY_LEVEL = 160
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [CodeCademyGroepB3].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [CodeCademyGroepB3] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [CodeCademyGroepB3] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [CodeCademyGroepB3] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [CodeCademyGroepB3] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [CodeCademyGroepB3] SET ARITHABORT OFF 
GO
ALTER DATABASE [CodeCademyGroepB3] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [CodeCademyGroepB3] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [CodeCademyGroepB3] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [CodeCademyGroepB3] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [CodeCademyGroepB3] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [CodeCademyGroepB3] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [CodeCademyGroepB3] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [CodeCademyGroepB3] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [CodeCademyGroepB3] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [CodeCademyGroepB3] SET  ENABLE_BROKER 
GO
ALTER DATABASE [CodeCademyGroepB3] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [CodeCademyGroepB3] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [CodeCademyGroepB3] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [CodeCademyGroepB3] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [CodeCademyGroepB3] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [CodeCademyGroepB3] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [CodeCademyGroepB3] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [CodeCademyGroepB3] SET RECOVERY FULL 
GO
ALTER DATABASE [CodeCademyGroepB3] SET  MULTI_USER 
GO
ALTER DATABASE [CodeCademyGroepB3] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [CodeCademyGroepB3] SET DB_CHAINING OFF 
GO
ALTER DATABASE [CodeCademyGroepB3] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [CodeCademyGroepB3] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [CodeCademyGroepB3] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [CodeCademyGroepB3] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'CodeCademyGroepB3', N'ON'
GO
ALTER DATABASE [CodeCademyGroepB3] SET QUERY_STORE = ON
GO
ALTER DATABASE [CodeCademyGroepB3] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO
USE [CodeCademyGroepB3]
GO
/****** Object:  Table [dbo].[Content]    Script Date: 29/03/2024 23:35:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Content](
	[contentID] [int] NOT NULL,
	[naamCursus] [nvarchar](255) NULL,
	[datum] [date] NULL,
	[titel] [nvarchar](max) NULL,
	[beschrijving] [nvarchar](max) NULL,
	[status] [nvarchar](max) NULL,
 CONSTRAINT [PK_Content] PRIMARY KEY CLUSTERED 
(
	[contentID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Cursist]    Script Date: 29/03/2024 23:35:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Cursist](
	[emailadres] [nvarchar](255) NOT NULL,
	[geboortedatum] [date] NULL,
	[adres] [nvarchar](max) NULL,
	[naam] [nvarchar](max) NULL,
	[woonplaats] [nvarchar](max) NULL,
	[land] [nvarchar](max) NULL,
	[geslacht] [nvarchar](max) NULL,
	[postcode] [nvarchar](max) NULL,
	[huisnummer] [nvarchar](max) NULL,
 CONSTRAINT [PK_Cursist] PRIMARY KEY CLUSTERED 
(
	[emailadres] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Cursus]    Script Date: 29/03/2024 23:35:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Cursus](
	[naamCursus] [nvarchar](255) NOT NULL,
	[onderwerp] [nvarchar](max) NULL,
	[introTekst] [nvarchar](max) NULL,
	[niveau] [nvarchar](max) NULL,
 CONSTRAINT [PK_Cursus] PRIMARY KEY CLUSTERED 
(
	[naamCursus] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Inschrijving]    Script Date: 29/03/2024 23:35:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Inschrijving](
	[inschrijvingID] [int] IDENTITY(1,1) NOT NULL,
	[naamCursus] [nvarchar](255) NULL,
	[datum] [date] NULL,
	[emailadres] [nvarchar](255) NULL,
 CONSTRAINT [PK__Inschrij__DD5D3AE19DC11258] PRIMARY KEY CLUSTERED 
(
	[inschrijvingID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Module]    Script Date: 29/03/2024 23:35:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Module](
	[contentID] [int] NOT NULL,
	[titel] [nvarchar](255) NOT NULL,
	[versie] [int] NOT NULL,
	[naamContactPersoon] [nvarchar](max) NULL,
	[emailContactPersoon] [nvarchar](max) NULL,
	[volgNummer] [int] NULL,
	[status] [nvarchar](max) NULL,
	[beschrijving] [nvarchar](max) NULL,
 CONSTRAINT [PK_Module_1] PRIMARY KEY CLUSTERED 
(
	[contentID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Voortgang]    Script Date: 29/03/2024 23:35:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Voortgang](
	[emailadres] [nvarchar](255) NOT NULL,
	[naamCursus] [nvarchar](255) NOT NULL,
	[contentID] [int] NOT NULL,
	[voortgangPercentage] [decimal](5, 2) NULL,
 CONSTRAINT [PK_Voortgang_1] PRIMARY KEY CLUSTERED 
(
	[emailadres] ASC,
	[contentID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Webcast]    Script Date: 29/03/2024 23:35:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Webcast](
	[contentID] [int] NOT NULL,
	[titel] [nvarchar](255) NOT NULL,
	[tijdsDuur] [int] NULL,
	[datumPublicatie] [date] NULL,
	[URL] [nvarchar](max) NULL,
	[naamSpreker] [nvarchar](max) NULL,
	[organisatieSpreker] [nvarchar](max) NULL,
	[views] [int] NULL,
	[status] [nvarchar](max) NULL,
 CONSTRAINT [PK_Webcast_1] PRIMARY KEY CLUSTERED 
(
	[contentID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
INSERT [dbo].[Content] ([contentID], [naamCursus], [datum], [titel], [beschrijving], [status]) VALUES (1, N'Programmeren 2', CAST(N'2023-10-21' AS Date), N'Opdracht Codecademy', N'Hier leer je over de Codecademy groepsopdracht', N'actief')
GO
INSERT [dbo].[Content] ([contentID], [naamCursus], [datum], [titel], [beschrijving], [status]) VALUES (2, N'Duurzame Ontwikkeling', CAST(N'2023-10-21' AS Date), N' Duurzaamheid Webcast', N'Deze webcast gaat over Duurzaamheid in de IT sector', N'concept')
GO
INSERT [dbo].[Content] ([contentID], [naamCursus], [datum], [titel], [beschrijving], [status]) VALUES (3, N'Relationele Databases 1', CAST(N'2023-08-19' AS Date), N'SSMS', N'Hier leer je hoe je gebruik kan maken van SQL Server Management Studio', N'gearchiveerd')
GO
INSERT [dbo].[Content] ([contentID], [naamCursus], [datum], [titel], [beschrijving], [status]) VALUES (4, N'Programmeren 2', CAST(N'2023-10-21' AS Date), N'Codecademy Webcast', N'Hier leer je hoe je je codecademy project opstart', N'actief')
GO
INSERT [dbo].[Content] ([contentID], [naamCursus], [datum], [titel], [beschrijving], [status]) VALUES (5, N'Programmeren 2', CAST(N'2023-10-21' AS Date), N'Programmeren Tentamen Voorbereiding Webcast', N'Deze webcast dient als hulp bij het voorbereiden voor het tentamen', N'gearchiveerd')
GO
INSERT [dbo].[Content] ([contentID], [naamCursus], [datum], [titel], [beschrijving], [status]) VALUES (6, N'Relationele Databases 1', CAST(N'2023-10-21' AS Date), N'Databases Tentamen Voorbereiding Webcast', N'Deze webcast dient als hulp bij het voorbereiden voor het tentamen', N'gearchiveerd')
GO
INSERT [dbo].[Content] ([contentID], [naamCursus], [datum], [titel], [beschrijving], [status]) VALUES (7, N'Duurzame Ontwikkeling', CAST(N'2023-10-21' AS Date), N'Duurzaamheid Tentamen Voorbereiding Webcast', N'Deze webcast dient als hulp bij het voorbereiden voor het tentamen', N'gearchiveerd')
GO
INSERT [dbo].[Cursist] ([emailadres], [geboortedatum], [adres], [naam], [woonplaats], [land], [geslacht], [postcode], [huisnummer]) VALUES (N'bashuis@hotmail.nl', CAST(N'2003-04-19' AS Date), N'huisstraat', N'bas huis', N'amsterdam', N'nederland', N'man', N'3310 PO', N'112')
GO
INSERT [dbo].[Cursist] ([emailadres], [geboortedatum], [adres], [naam], [woonplaats], [land], [geslacht], [postcode], [huisnummer]) VALUES (N'kevinavans@avans.nl', CAST(N'2004-01-01' AS Date), N'oswijk', N'kevin avans', N'breda', N'nederland', N'man', N'3311 IO', N'118')
GO
INSERT [dbo].[Cursist] ([emailadres], [geboortedatum], [adres], [naam], [woonplaats], [land], [geslacht], [postcode], [huisnummer]) VALUES (N'ot.denbeste@gmail.com', CAST(N'2000-02-10' AS Date), N'hoekstraat', N'Ottelien den Beste', N'breda', N'Nederland', N'Vrouw', N'1111 PO', N'12')
GO
INSERT [dbo].[Cursist] ([emailadres], [geboortedatum], [adres], [naam], [woonplaats], [land], [geslacht], [postcode], [huisnummer]) VALUES (N'wa.vanburen@gmail.com', CAST(N'1968-02-28' AS Date), N'buurstraat', N'W.A. van Buren', N'breda', N'Nederland', N'Man', N'1122 BU', N'120')
GO
INSERT [dbo].[Cursus] ([naamCursus], [onderwerp], [introTekst], [niveau]) VALUES (N'Duurzame Ontwikkeling', N'Smart cities', N'In deze cursus behandelen wij maatschappelijke problemen zoals Smart cities', N'expert')
GO
INSERT [dbo].[Cursus] ([naamCursus], [onderwerp], [introTekst], [niveau]) VALUES (N'Programmeren 2', N'Java', N'In deze cursus behandelen wij vooral Java voor de eindopdraht Codecademy', N'gevorderd')
GO
INSERT [dbo].[Cursus] ([naamCursus], [onderwerp], [introTekst], [niveau]) VALUES (N'Relationele Databases 1', N'Databases beheren', N'In deze cursus leer je hoe je een database beheert en onderhoudt', N'expert')
GO
SET IDENTITY_INSERT [dbo].[Inschrijving] ON 
GO
INSERT [dbo].[Inschrijving] ([inschrijvingID], [naamCursus], [datum], [emailadres]) VALUES (1, N'Programmeren 2', CAST(N'2023-06-12' AS Date), N'bashuis@hotmail.nl')
GO
INSERT [dbo].[Inschrijving] ([inschrijvingID], [naamCursus], [datum], [emailadres]) VALUES (3, N'Relationele Databases 1', CAST(N'2023-06-13' AS Date), N'bashuis@hotmail.nl')
GO
INSERT [dbo].[Inschrijving] ([inschrijvingID], [naamCursus], [datum], [emailadres]) VALUES (5, N'Duurzame Ontwikkeling', CAST(N'2024-02-26' AS Date), N'bashuis@hotmail.nl')
GO
INSERT [dbo].[Inschrijving] ([inschrijvingID], [naamCursus], [datum], [emailadres]) VALUES (12, NULL, CAST(N'2024-02-26' AS Date), N'bashuis@hotmail.nl')
GO
INSERT [dbo].[Inschrijving] ([inschrijvingID], [naamCursus], [datum], [emailadres]) VALUES (14, N'Relationele Databases 1', CAST(N'2019-06-06' AS Date), N'ot.denbeste@gmail.com')
GO
INSERT [dbo].[Inschrijving] ([inschrijvingID], [naamCursus], [datum], [emailadres]) VALUES (15, N'Relationele Databases 1', CAST(N'2020-07-07' AS Date), N'ot.denbeste@gmail.com')
GO
INSERT [dbo].[Inschrijving] ([inschrijvingID], [naamCursus], [datum], [emailadres]) VALUES (16, N'Programmeren 2', CAST(N'2024-11-21' AS Date), N'wa.vanburen@gmail.com')
GO
SET IDENTITY_INSERT [dbo].[Inschrijving] OFF
GO
INSERT [dbo].[Module] ([contentID], [titel], [versie], [naamContactPersoon], [emailContactPersoon], [volgNummer], [status], [beschrijving]) VALUES (1, N'Opdracht Codecademy', 1, N'Kevin Lam', N'kevinlam@avans.nl', 1, N'actief', N'Hier leer je over de Codecademy groepsopdracht')
GO
INSERT [dbo].[Module] ([contentID], [titel], [versie], [naamContactPersoon], [emailContactPersoon], [volgNummer], [status], [beschrijving]) VALUES (3, N'SSMS', 1, N'Jan Avans', N'janavans@avans.nl', 2, N'gearchiveerd', N'Hier leer je hoe je gebruik kan maken van SQL Server Management Studio')
GO
INSERT [dbo].[Voortgang] ([emailadres], [naamCursus], [contentID], [voortgangPercentage]) VALUES (N'bashuis@hotmail.nl', N'Programmeren 2', 1, CAST(70.00 AS Decimal(5, 2)))
GO
INSERT [dbo].[Voortgang] ([emailadres], [naamCursus], [contentID], [voortgangPercentage]) VALUES (N'bashuis@hotmail.nl', N'Duurzame Ontwikkeling', 2, CAST(30.00 AS Decimal(5, 2)))
GO
INSERT [dbo].[Voortgang] ([emailadres], [naamCursus], [contentID], [voortgangPercentage]) VALUES (N'bashuis@hotmail.nl', N'Relationele Databases 1', 3, CAST(100.00 AS Decimal(5, 2)))
GO
INSERT [dbo].[Webcast] ([contentID], [titel], [tijdsDuur], [datumPublicatie], [URL], [naamSpreker], [organisatieSpreker], [views], [status]) VALUES (2, N'Duurzaamheid Webcast', 150, CAST(N'2023-10-21' AS Date), N'avans.nl/duurzaam/webcast', N'Jan Vertonghen', N'Avans', 700, N'concept')
GO
INSERT [dbo].[Webcast] ([contentID], [titel], [tijdsDuur], [datumPublicatie], [URL], [naamSpreker], [organisatieSpreker], [views], [status]) VALUES (4, N'Codecademy Webcast', 80, CAST(N'2023-10-21' AS Date), N'avans.nl/programmeren2/webcastcodecademy', N'Kevin-Jan', N'Avans', 1200, N'actief')
GO
INSERT [dbo].[Webcast] ([contentID], [titel], [tijdsDuur], [datumPublicatie], [URL], [naamSpreker], [organisatieSpreker], [views], [status]) VALUES (5, N'Programmeren Tentamen Voorbereiding Webcast', 110, CAST(N'2023-10-21' AS Date), N'avans.nl/programmeren2/webcastprogrammeren', N'Marjolein Gerdes', N'Avans', 1900, N'gearchiveerd')
GO
INSERT [dbo].[Webcast] ([contentID], [titel], [tijdsDuur], [datumPublicatie], [URL], [naamSpreker], [organisatieSpreker], [views], [status]) VALUES (6, N'Databases Tentamen Voorbereiding Webcast', 160, CAST(N'2023-10-21' AS Date), N'avans.nl/programmeren2/webcastdatabases', N'Erik Kuipers', N'Avans', 1500, N'gearchiveerd')
GO
INSERT [dbo].[Webcast] ([contentID], [titel], [tijdsDuur], [datumPublicatie], [URL], [naamSpreker], [organisatieSpreker], [views], [status]) VALUES (7, N'Duurzaamheid Tentamen Voorbereiding Webcast', 200, CAST(N'2023-10-21' AS Date), N'avans.nl/programmeren2/webcastduurzaamheid', N'Jan Duurzaam', N'Avans', 900, N'gearchiveerd')
GO
ALTER TABLE [dbo].[Content]  WITH CHECK ADD  CONSTRAINT [FK_Content_Cursus] FOREIGN KEY([naamCursus])
REFERENCES [dbo].[Cursus] ([naamCursus])
GO
ALTER TABLE [dbo].[Content] CHECK CONSTRAINT [FK_Content_Cursus]
GO
ALTER TABLE [dbo].[Inschrijving]  WITH CHECK ADD  CONSTRAINT [FK_Inschrijving_Cursist] FOREIGN KEY([emailadres])
REFERENCES [dbo].[Cursist] ([emailadres])
GO
ALTER TABLE [dbo].[Inschrijving] CHECK CONSTRAINT [FK_Inschrijving_Cursist]
GO
ALTER TABLE [dbo].[Inschrijving]  WITH CHECK ADD  CONSTRAINT [FK_Inschrijving_Cursus] FOREIGN KEY([naamCursus])
REFERENCES [dbo].[Cursus] ([naamCursus])
GO
ALTER TABLE [dbo].[Inschrijving] CHECK CONSTRAINT [FK_Inschrijving_Cursus]
GO
ALTER TABLE [dbo].[Module]  WITH CHECK ADD  CONSTRAINT [FK_Module_Content] FOREIGN KEY([contentID])
REFERENCES [dbo].[Content] ([contentID])
GO
ALTER TABLE [dbo].[Module] CHECK CONSTRAINT [FK_Module_Content]
GO
ALTER TABLE [dbo].[Voortgang]  WITH CHECK ADD  CONSTRAINT [FK_Voortgang_Content] FOREIGN KEY([contentID])
REFERENCES [dbo].[Content] ([contentID])
GO
ALTER TABLE [dbo].[Voortgang] CHECK CONSTRAINT [FK_Voortgang_Content]
GO
ALTER TABLE [dbo].[Voortgang]  WITH CHECK ADD  CONSTRAINT [FK_Voortgang_Cursist] FOREIGN KEY([emailadres])
REFERENCES [dbo].[Cursist] ([emailadres])
GO
ALTER TABLE [dbo].[Voortgang] CHECK CONSTRAINT [FK_Voortgang_Cursist]
GO
ALTER TABLE [dbo].[Voortgang]  WITH CHECK ADD  CONSTRAINT [FK_Voortgang_Cursus] FOREIGN KEY([naamCursus])
REFERENCES [dbo].[Cursus] ([naamCursus])
GO
ALTER TABLE [dbo].[Voortgang] CHECK CONSTRAINT [FK_Voortgang_Cursus]
GO
ALTER TABLE [dbo].[Webcast]  WITH CHECK ADD  CONSTRAINT [FK_Webcast_Content] FOREIGN KEY([contentID])
REFERENCES [dbo].[Content] ([contentID])
GO
ALTER TABLE [dbo].[Webcast] CHECK CONSTRAINT [FK_Webcast_Content]
GO
USE [master]
GO
ALTER DATABASE [CodeCademyGroepB3] SET  READ_WRITE 
GO
