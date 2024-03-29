/* Deze database was oorspronkelijk voor een ander groepje bedoelt, echter hebben wij op advies van Erik Kuipers, deze nu overgenomen om op afstand met de database te kunnen werken.
Wij hebben de database helemaal leeggehaald en vervangen met onze eigen data (tabellen, diagrammen, FKs, PKs, testdata, etc.) Het enige wat over is gebleven is de naam van de database, omdat wij geen toegang hebben deze te veranderen.*/


USE [master]
GO
/****** Object:  Database [CodeCademyGroepB3]    Script Date: 29/03/2024 12:32:03 ******/
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
/****** Object:  Table [dbo].[Content]    Script Date: 29/03/2024 12:32:03 ******/
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
/****** Object:  Table [dbo].[Cursist]    Script Date: 29/03/2024 12:32:03 ******/
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
/****** Object:  Table [dbo].[Cursus]    Script Date: 29/03/2024 12:32:03 ******/
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
/****** Object:  Table [dbo].[Inschrijving]    Script Date: 29/03/2024 12:32:03 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Inschrijving](
	[inschrijvingID] [int] NOT NULL,
	[naamCursus] [nvarchar](255) NULL,
	[datum] [date] NULL,
	[emailadres] [nvarchar](255) NULL,
 CONSTRAINT [PK__Inschrij__DD5D3AE19DC11258] PRIMARY KEY CLUSTERED 
(
	[inschrijvingID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Module]    Script Date: 29/03/2024 12:32:03 ******/
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
 CONSTRAINT [PK_Module_1] PRIMARY KEY CLUSTERED 
(
	[contentID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Voortgang]    Script Date: 29/03/2024 12:32:03 ******/
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
/****** Object:  Table [dbo].[Webcast]    Script Date: 29/03/2024 12:32:03 ******/
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
 CONSTRAINT [PK_Webcast_1] PRIMARY KEY CLUSTERED 
(
	[contentID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
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
